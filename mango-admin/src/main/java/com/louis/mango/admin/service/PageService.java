package com.louis.mango.admin.service;

import com.louis.mango.admin.model.Page;
import com.louis.mango.admin.model.Resource;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.List;
import java.util.Map;

public interface PageService {
    PageResult getPages(PageRequest pageRequest, Map map);
    HttpResult addPage(Page page);
    HttpResult updatePage(Page page);
    HttpResult delPage(Long id);
    Page getById(Long id);
    Page getByPath(String path);
}
