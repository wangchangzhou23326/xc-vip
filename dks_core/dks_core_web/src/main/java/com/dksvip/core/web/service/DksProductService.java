package com.dksvip.core.web.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dksvip.core.dao.product.DaoProductInfoDks;
import com.dksvip.core.dao.product.DaoProductPackageDks;
import com.dksvip.core.dao.product.service.DaoProductInfoService;
import com.dksvip.core.dao.product.service.DaoProductPackageService;
import com.dksvip.core.decrypt.dks.DksAllApi;
import com.dksvip.core.web.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.mappings.servlet.FiltersMappingDescriptionProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author zhangcan
 * @date 2024/11/25 14:16
 */
@Slf4j
@Service
public class DksProductService {

    @Autowired
    private DksAllApi dksAllApi;

    @Autowired
    private DaoProductPackageService daoProductPackageService;

    @Autowired
    private DaoProductInfoService daoProductInfoService;

    /**
     * 获取套餐菜单列表
     */
    public List<DaoProductPackageDks> getClassicMenuProductList(String storeCode, Long userId, int count) {
        RedisUtil redisUtil = RedisUtil.getInstance();

        if (count >= 3) {
            redisUtil.incr("dks:syncProductPackageKeyError:" + userId, 1);
            return new ArrayList<DaoProductPackageDks>();
        }

        // 获取商品套餐
        JSONObject jsonObject = dksAllApi.getProductMenus(storeCode, "ts");
        JSONArray menusList = jsonObject.getJSONArray("data");

        for (int i = 0; i < menusList.size(); i++) {
            JSONObject js = menusList.getJSONObject(i);
            JSONArray packageList = js.getJSONArray("data");

            for (int j = 0; j < packageList.size(); j++) {
                JSONObject json = packageList.getJSONObject(j);
                DaoProductPackageDks dks = new DaoProductPackageDks();
                dks.setId(IdUtil.getSnowflakeNextId());
                dks.setUserId(userId);
                dks.setClassExtId(json.getStr("class_ext_id"));
                dks.setSystemId(json.getStr("system_id"));
                dks.setStoreCode(storeCode);
                dks.setName(json.getStr("name"));
                dks.setImage(json.getStr("dish_image_url"));
                dks.setRealPrice(json.getBigDecimal("real_price"));
                dks.setPrice(json.getBigDecimal("price"));
                dks.setClassType(json.getInt("class_type"));
                dks.setCardType(json.getInt("card_type"));
                dks.setProductCategory(json.getInt("product_category"));
                dks.setIsOption(json.getInt("is_option"));
                dks.setProductPackageDesc(json.getInt("desc"));
                dks.setChildClassId(json.getStr("child_class_id"));
                dks.setChildClassName(json.getStr("child_class_name"));
                dks.setCreateTime(new Date());
                dks.setUpdateTime(new Date());

                // 查询套餐是否存在、不存在则新增、存在则更新
                QueryWrapper<DaoProductPackageDks> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", userId);
                queryWrapper.eq("system_id", json.getStr("system_id"));
                queryWrapper.eq("class_ext_id", json.getStr("class_ext_id"));
                DaoProductPackageDks productPackageDks = daoProductPackageService.getOne(queryWrapper);

                if (dks.getName().equals("尊享年卡") || dks.getName().equals("尊享季卡")) {
                    continue;
                }

                if (productPackageDks == null) {
                    daoProductPackageService.save(dks);
                } else {
                    dks.setId(productPackageDks.getId());
                    daoProductPackageService.updateById(dks);
                }
            }
        }

        // 循环完成之后、查询出已经入库的套餐
        return daoProductPackageService.list(new QueryWrapper<DaoProductPackageDks>().eq("user_id", userId));

    }

    /**
     * 查询套餐下的商品
     *
     * @param productPackageDks
     * @param storeCode
     * @param userId
     */
    public void saveRxProductPackage(DaoProductPackageDks productPackageDks, String storeCode, Long userId) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        log.info("执行异步任务syncProductPackageKey：{}", Thread.currentThread().getName());
//        String productId = jsonObject.getStr("productId");

        JSONObject ts = dksAllApi.getProductMenuDetail(storeCode, "ts", productPackageDks.getClassExtId(), productPackageDks.getSystemId(), productPackageDks.getChildClassId());

        // 拿到套餐下的商品
        JSONObject data = ts.getJSONObject("data");

        JSONArray fixedList = data.getJSONArray("fixed_list");
        for (int i = 0; i < fixedList.size(); i++) {
            JSONObject jsonObject = fixedList.getJSONObject(i);
            DaoProductInfoDks dks = new DaoProductInfoDks();
            dks.setId(IdUtil.getSnowflakeNextId());
            dks.setProductName(jsonObject.getStr("name"));
            dks.setMainImageUrl(jsonObject.getStr("image_url"));
            dks.setProductPackageId(productPackageDks.getId());
            dks.setSystemId(productPackageDks.getSystemId());
            dks.setPrice(BigDecimal.ZERO);
            dks.setStoreCode(storeCode);
            dks.setIncludeRound(0);
            dks.setIsDefault(0);
            dks.setCreateTime(new Date());
            dks.setUpdateTime(new Date());
            dks.setUserId(userId);
            daoProductInfoService.save(dks);
//            QueryWrapper<DaoProductInfoDks> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("product_package_id", productPackageDks.getId());
//            queryWrapper.eq("is_default", 0);
//            queryWrapper.eq("product_name", dks.getProductName());
//            List<DaoProductInfoDks> list = daoProductInfoService.list(queryWrapper);

            // 判断是否在数据库存在、如果小于1、则直接新增
//            if (list.size()<1) {
//                daoProductInfoService.save()
//            }
        }

        JSONArray optionList = data.getJSONArray("option_list");
        for (int i = 0; i < optionList.size(); i++) {
            JSONObject jsonObject = optionList.getJSONObject(i);
            JSONArray condimentItemList = jsonObject.getJSONArray("condiment_item_list");
            for (int j=0;j< condimentItemList.size();j++){
                JSONObject json = condimentItemList.getJSONObject(j);

                // 封装的外围循环数据
                DaoProductInfoDks dks = new DaoProductInfoDks();
                dks.setId(IdUtil.getSnowflakeNextId());
                dks.setRoundId(jsonObject.getStr("round"));
                dks.setRoundName(jsonObject.getStr("round_name"));
                dks.setRoundBasePrice(jsonObject.getBigDecimal("base_price"));
                dks.setRoundBaseRealPrice(jsonObject.getBigDecimal("base_real_price"));
                dks.setProductPackageId(productPackageDks.getId());
                dks.setStoreCode(storeCode);
                dks.setIncludeRound(1);


                // 封装的内循环数据
                dks.setSystemId(json.getStr("system_id"));
                dks.setProductName(json.getStr("name"));
                dks.setPrice(json.getBigDecimal("price"));
                dks.setRealPrice(json.getBigDecimal("real_price"));
                dks.setMainImageUrl(json.getStr("image_url"));
                dks.setCreateTime(new Date());
                dks.setUpdateTime(new Date());
                if (json.getBool("is_default")){
                    dks.setIsDefault(0);
                }else {
                    dks.setIsDefault(1);
                }
                dks.setUserId(userId);
                daoProductInfoService.save(dks);
            }
        }


    }
}


