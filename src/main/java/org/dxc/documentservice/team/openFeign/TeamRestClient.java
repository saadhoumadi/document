package org.dxc.documentservice.team.openFeign;

import org.dxc.documentservice.team.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "identity-service")
public interface TeamRestClient {
    @GetMapping("identity-service/team/all")
    List<Team> getAllTeams();
}
