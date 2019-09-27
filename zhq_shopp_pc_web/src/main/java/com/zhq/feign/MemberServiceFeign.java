package com.zhq.feign;

import com.zhq.api.entity.UserEntity;
import com.zhq.api.service.UserService;
import com.zhq.common.base.ResponseBase;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @program: zhq_shopp_parent
 * @description: 会员服务
 * @author: HQ Zheng
 * @create: 2019-09-27 17:13
 */
@Component
@FeignClient(value = "member")
public interface MemberServiceFeign extends UserService {

}
