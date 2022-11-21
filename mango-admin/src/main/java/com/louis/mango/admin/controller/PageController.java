package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.Page;
import com.louis.mango.admin.model.Resource;
import com.louis.mango.admin.service.PageService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("page")
public class PageController {

    @Autowired
    PageService pageService;

    @PostMapping(value = "list")
    public HttpResult getPages(@RequestBody Map map) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageSize((Integer) map.get("pageSize"));
        pageRequest.setPageNum((Integer) map.get("pageNum"));
        return HttpResult.ok(pageService.getPages(pageRequest, map));
    }

    @PostMapping(value = "/add")
    public HttpResult addPage(@RequestBody Page page) {
        return pageService.addPage(page);
    }

    @PostMapping(value = "/update")
    public HttpResult updatePage(@RequestBody Page page) {
        return pageService.updatePage(page);
    }

    @GetMapping(value = "/del")
    public HttpResult delPage(@RequestParam Long id) {
        return pageService.delPage(id);
    }

    @GetMapping(value = "/{id}")
    public HttpResult getById(@PathVariable Long id) {
        return HttpResult.ok(pageService.getById(id));
    }

    @GetMapping(value = "/detail")
    public HttpResult getByPath(@RequestParam String path) {
        return HttpResult.ok(pageService.getByPath(path));
    }
}
