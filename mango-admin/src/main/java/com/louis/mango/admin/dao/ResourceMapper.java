package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.Resource;

import java.util.LinkedHashMap;
import java.util.List;

public interface ResourceMapper {
    List<Resource> getResources(LinkedHashMap map);
    Resource getByCode(String code);
    void addResource(Resource resource);
    void updateResource(Resource resource);
    void delResource(Long id);
    Resource getById(Long id);
}
