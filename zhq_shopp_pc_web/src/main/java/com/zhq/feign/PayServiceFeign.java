package com.zhq.feign;

import com.zhq.api.service.PayService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("pay")
public interface PayServiceFeign extends PayService {

}
