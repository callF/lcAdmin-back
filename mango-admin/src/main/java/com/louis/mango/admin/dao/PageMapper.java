package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.Atom;
import com.louis.mango.admin.model.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface PageMapper {
    void addPage(Page page);

    List<Page> getPages(LinkedHashMap map);

    Page getById(Long id);

    void delPage(Long id);

    Page getByPath(String path);
}
