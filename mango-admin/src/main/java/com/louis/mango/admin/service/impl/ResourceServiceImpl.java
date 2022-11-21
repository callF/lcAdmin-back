package com.louis.mango.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.louis.mango.admin.dao.AtomMapper;
import com.louis.mango.admin.dao.ResourceMapper;
import com.louis.mango.admin.model.Atom;
import com.louis.mango.admin.model.Resource;
import com.louis.mango.admin.service.AtomService;
import com.louis.mango.admin.service.ResourceService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private AtomService atomService;

    @Override
    public PageResult getResources(PageRequest pageRequest, Map map) {
        return MybatisPageHelper.findPage(pageRequest, resourceMapper, "getResources", map);
    }

    @Override
    public HttpResult addResource(Resource resource) {
        resource.setCode(generateResourceCode());
        resourceMapper.addResource(resource);
        return HttpResult.ok(null);
    }

    @Override
    public HttpResult updateResource(Resource resource) {
        resource.setConfigObjStr(JSONObject.toJSONString(resource.getConfigObj()));
        resourceMapper.updateResource(resource);
        return HttpResult.ok(null);
    }

    @Override
    public HttpResult delResource(Long id) {
        resourceMapper.delResource(id);
        return HttpResult.ok(null);
    }

    @Override
    public Resource getById(Long id) {
        Resource resource = resourceMapper.getById(id);
        Map configObj = (Map) JSONObject.parse(resource.getConfigObjStr());
        resource.setConfigObj(configObj);
        resource.setAtom(atomService.getById(resource.getAtomId()));
        return resource;
    }

    public Object getByCode(String code) {
        return resourceMapper.getByCode(code);
    }

    public String generateResourceCode() {
        String code = "Resource_" + System.currentTimeMillis();
        if (getByCode(code) != null) {
            return generateResourceCode();
        }
        System.out.println(code);
        return code;
    }
}
