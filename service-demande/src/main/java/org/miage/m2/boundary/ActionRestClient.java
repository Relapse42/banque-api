package org.miage.m2.boundary;

import org.miage.m2.entity.Action;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://service-action")
public interface ActionRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/actions/{id}")
    Action get(@PathVariable("id") String id);
}
