package org.dxc.documentservice.user.openFeign;

import org.dxc.documentservice.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "identity-service")
public interface UserRestClient {
    @GetMapping("identity-service/user/{userId}")
    User userById(@PathVariable Long userId);
}
