package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.Resource;
import com.louis.mango.admin.service.ResourceService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/list")
    public HttpResult getResources(@RequestBody Map map) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum((Integer) map.get("pageNum"));
        pageRequest.setPageSize((Integer) map.get("pageSize"));
        return HttpResult.ok(resourceService.getResources(pageRequest, map));
    }

    @PostMapping(value = "/add")
    public HttpResult addResource(@RequestBody Resource resource) {
        return resourceService.addResource(resource);
    }

    @PostMapping(value = "/update")
    public HttpResult updateResource(@RequestBody Resource resource) {
        return resourceService.updateResource(resource);
    }

    @GetMapping(value = "/del")
    public HttpResult delResource(@RequestParam Long id) {
        return resourceService.delResource(id);
    }

    @GetMapping(value = "/{id}")
    public HttpResult getById(@PathVariable Long id) {
        return HttpResult.ok(resourceService.getById(id));
    }
}
