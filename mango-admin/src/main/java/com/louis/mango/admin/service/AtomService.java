package com.louis.mango.admin.service;

import com.louis.mango.admin.model.Atom;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.Map;

public interface AtomService {
    HttpResult addAtom(Map map);

    PageResult getAtoms(PageRequest pageRequest, Map map);

    HttpResult delAtom(Long id);

    Atom getById(Long id);
}
