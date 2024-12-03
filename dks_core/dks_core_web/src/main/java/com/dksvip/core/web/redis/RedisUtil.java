package com.dksvip.core.web.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @author
 * @since 2024-09-06
 */
@Component
@Log4j2
public final class RedisUtil {

    private RedisUtil() {

    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private SpringApplicationContextHolder a ;

    //使用execute执行lua脚本方法
    public Long execute(RedisScript<Long> script, List<String> keys, int count, int time) {
        return redisTemplate.execute(script, keys, count, time);
    }


    /**
     * 获取Redis操作实例，适用于任何需要操作Redis的场景。
     * 尤其对于不受Spring容器管理的实例，需要通过此方法获取Redis实例。
     *
     * @return redis操作实例
     */
    public static RedisUtil getInstance() {
        return (RedisUtil) SpringApplicationContextHolder.getSpringBean("redisUtil");
    }

    /**
     * 设置指定key的过期时间
     *
     * @param key      键
     * @param time     时间
     * @param timeUnit 时间单位，建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 获取指定key的过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒), 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true:存在,false:不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                Collection<String> keys=new ArrayList<>();
                keys.addAll((Collection<? extends String>) CollectionUtils.arrayToList(key));
                redisTemplate.delete(keys);
            }
        }
    }

    /**
     * 获取缓存值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存值
     *
     * @param key   键
     * @param value 值
     * @return true:成功,false:失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 放入缓存并设置时间
     *
     * @param key      键
     * @param value    值
     * @param time     过期时间,time要大于0,如果time小于等于0将设置无限期
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return true:成功,false:失败
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 当key对应的值不存在时放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true:成功,false:失败
     */
    public boolean setIfAbsent(String key, Object value) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 当key对应的值不存在时放入缓存并设置过期时间
     *
     * @param key      键
     * @param value    值
     * @param time     时间,time要大于0,如果time小于等于0将设置无限期
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return true:成功,false:失败
     */
    public boolean setIfAbsent(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
            } else {
                return setIfAbsent(key, value);
            }

        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);

        }
        return false;
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true:成功,false:失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * HashSet并设置过期时间
     *
     * @param key      键
     * @param map      对应多个键值
     * @param time     时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return true:成功,false:失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error("");
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true:成功,false:失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建,存在则不创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true:成功,false:失败
     */
    public boolean hsetIfAbsent(String key, String item, Object value) {
        try {
            return redisTemplate.opsForHash().putIfAbsent(key, item, value);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key      键
     * @param item     项
     * @param value    值
     * @param time     时间,如果已存在的hash表有时间,这里将会替换原有的时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return true:成功,false:失败
     */
    public boolean hset(String key, String item, Object value, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建,存在则不创建
     *
     * @param key      键
     * @param item     项
     * @param value    值
     * @param time     时间,如果已存在的hash表有时间,这里将会替换原有的时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return true:成功,false:失败
     */
    public boolean hsetIfAbsent(String key, String item, Object value, long time, TimeUnit timeUnit) {
        try {
            boolean success = redisTemplate.opsForHash().putIfAbsent(key, item, value);
            if (success && time > 0) {
                expire(key, time, timeUnit);
            }
            return success;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个,不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true:存在,false:不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个,并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递增 如果不存在,就会创建一个,并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public long hincr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少几(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少几(小于0)
     * @return
     */
    public long hdecr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存并设置过期时间
     *
     * @param key      键
     * @param time     时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @param values   值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, TimeUnit timeUnit, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0){
                expire(key, time, timeUnit);
            }
            return count;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return null;
        }
    }

    public Object popItemFromList(String key) {
        // 从列表的尾部弹出一个元素，即获取并删除列表最后一个元素
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return null;
        }
    }

    public Object lPop(String key) {
        // 从列表的尾部弹出一个元素，即获取并删除列表最后一个元素
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key      键
     * @param value    值
     * @param time     时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return
     */
    public boolean rpush(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0){
                expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key      键
     * @param value    值
     * @param time     时间
     * @param timeUnit 时间单位,建议只使用${@link TimeUnit#DAYS,TimeUnit#HOURS,TimeUnit#MINUTES,TimeUnit#SECONDS}
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0){
                expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }
    
    /**
     * blpop
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public Object blpop(String key, Long time, TimeUnit timeUnit) {
        return redisTemplate.opsForList().leftPop(key, time, timeUnit);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error("Redis Cache Operate Error: ", e);
            return 0;
        }
    }

    /**
     * 移除key过期时间
     *
     * @param key
     * @return
     */
    public Boolean persist(final String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 根据指定的key获取所有的hash键
     *
     * @param key
     * @return
     */
    public Set<Object> hKeys(final String key) {
        return redisTemplate.opsForHash().keys(key);
    }


    /**
     * 通过lua脚本从set中获取一个值，然后将其删除
     *
     * @param key
     * @param value
     * @return
     */

    //通过lua脚本从set中获取一个值，然后将其删除
    public Object getAndRemoveFromSet(String key) {
        /*// Lua脚本查询并删除集合中的元素
        String luaScript =
                "local members = redis.call('spop', KEYS[1]) " +
                        "return members";

        DefaultRedisScript<Object> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Object.class);

        // 使用RedisTemplate执行Lua脚本
        Object result = redisTemplate.execute(script, Collections.singletonList(key));*/
        return redisTemplate.opsForSet().pop(key);
    }

}
