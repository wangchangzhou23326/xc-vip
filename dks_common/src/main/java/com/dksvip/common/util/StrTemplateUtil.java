package com.dksvip.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板替换
 */
public class StrTemplateUtil {

    /**
     * Map<String, Object> dataMap = new HashMap<>();
     *         dataMap.put("userName", "Jyang");
     *         dataMap.put("password", "admin");
     *         String template = "用户名称：${userName}，用户密码：${password}";
     * 替换结果：用户名称：Jyang，用户密码：admin
     * @param template
     * @param params
     * @return
     */
    public static String replace(String template, Map<String,Object> params) {
        if (template.startsWith("$") || template.contains("$")) {
            Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}");
            Matcher matcher = pattern.matcher(template);
            StringBuffer result = new StringBuffer();
            // 根据正则替换
            while (matcher.find()) {
                String replacement = Objects.toString(params.get(matcher.group(1)), "");
                matcher.appendReplacement(result, replacement);
            }
            matcher.appendTail(result);
            return Objects.toString(result);
        } else {
            return params.get("domainName")+"?secret="+params.get("code");
        }
    }


    public static Map<String, Object> toMap(Object entity) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = entity.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // 使得私有字段也可以访问
            try {
                map.put(field.getName(), field.get(entity));
            } catch (IllegalAccessException e) {
                // 这个异常不应该发生，因为我们已经调用了setAccessible(true)
                throw new RuntimeException(e);
            }
        }
        return map;
    }

}
