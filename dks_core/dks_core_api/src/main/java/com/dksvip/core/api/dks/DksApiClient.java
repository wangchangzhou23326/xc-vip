package com.dksvip.core.api.dks;

import cn.hutool.json.JSONObject;
import com.dksvip.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 瑞幸接口对应内部和外部、继承该接口
 */
@FeignClient(name = "DksApiClient",path = "dksAllApi", url = "${dks.apiUrl}")
public interface DksApiClient {

}
