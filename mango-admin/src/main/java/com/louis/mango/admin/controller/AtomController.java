package com.louis.mango.admin.controller;

import com.louis.mango.admin.service.AtomService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("atom")
public class AtomController {
    @Autowired
    private AtomService atomService;

    @PostMapping(value = "/add")
    public HttpResult addAtom(@RequestBody Map map) {
        return atomService.addAtom(map);
    }

    @PostMapping(value = "/list")
    public HttpResult getAtoms(@RequestBody Map map) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum((Integer) map.get("pageNum"));
        pageRequest.setPageSize((Integer) map.get("pageSize"));
        return HttpResult.ok(atomService.getAtoms(pageRequest, map));
    }

    @GetMapping(value = "/del")
    public HttpResult delAtom(@RequestParam Long id) {
        return atomService.delAtom(id);
    }
}
