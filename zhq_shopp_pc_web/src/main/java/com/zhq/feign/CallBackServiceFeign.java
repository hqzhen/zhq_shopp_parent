package com.zhq.feign;

import com.zhq.api.service.CallBackService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient("pay")
@Component
public interface CallBackServiceFeign extends CallBackService {
}
