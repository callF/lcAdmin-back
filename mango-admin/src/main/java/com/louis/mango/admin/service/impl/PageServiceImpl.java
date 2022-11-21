package com.louis.mango.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.louis.mango.admin.dao.PageMapper;
import com.louis.mango.admin.dao.ResourceMapper;
import com.louis.mango.admin.model.Page;
import com.louis.mango.admin.model.Resource;
import com.louis.mango.admin.service.PageService;
import com.louis.mango.admin.service.ResourceService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageMapper pageMapper;

    @Autowired
    ResourceService resourceService;
    @Override
    public PageResult getPages(PageRequest pageRequest, Map map) {
        return MybatisPageHelper.findPage(pageRequest, pageMapper, "getPages", map);
    }

    @Override
    public HttpResult addPage(Page page) {
        String path = page.getPath();
        if (pageMapper.getByPath(path) != null) {
            return HttpResult.error("当前路径已存在");
        }
        page.setCListStr(JSONObject.toJSONString(page.getCList()));
        pageMapper.addPage(page);
        return HttpResult.ok(null);
    }

    @Override
    public HttpResult updatePage(Page page) {
        return null;
    }

    @Override
    public HttpResult delPage(Long id) {
        pageMapper.delPage(id);
        return HttpResult.ok(null);
    }

    @Override
    public Page getById(Long id) {
        return pageMapper.getById(id);
    }

    @Override
    public Page getByPath(String path) {
        Page page = pageMapper.getByPath(path);
        JSONArray cList = (JSONArray) JSONArray.parse(page.getCListStr());
        List<Resource> resourceList = new ArrayList();
        for (int i = 0; i < cList.size(); i++) {
            resourceList.add(resourceService.getById(cList.getLong(i)));
        }
        page.setResourceList(resourceList);
        return page;
    }
}
