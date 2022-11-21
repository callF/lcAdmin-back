package com.louis.mango.admin.service;

import com.louis.mango.admin.model.Resource;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.Map;

public interface ResourceService {
    PageResult getResources(PageRequest pageRequest, Map map);
    HttpResult addResource(Resource resource);
    HttpResult updateResource(Resource resource);
    HttpResult delResource(Long id);
    Resource getById(Long id);
}
