package com.louis.mango.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.louis.mango.admin.dao.AtomMapper;
import com.louis.mango.admin.model.Atom;
import com.louis.mango.admin.service.AtomService;
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
public class AtomServiceImpl implements AtomService {
    @Autowired
    private AtomMapper atomMapper;
    @Override
    public HttpResult addAtom(Map map) {
        String jsonStr = (String)map.get("config");
        JSONObject jsonObject = JSONObject.parseObject(jsonStr, Feature.OrderedField);
        Atom atom = new Atom();
        String type = (String) jsonObject.get("type");
        // 校验是否存在
        Atom atom1 = atomMapper.getByType(type);
        if (atom1 != null) {
            return HttpResult.error("已存在该类型");
        }
        String properties = JSONObject.toJSONString(jsonObject.get("properties"));
        atom.setDescription((String) jsonObject.get("desc"));
        atom.setType(type);
        atom.setProperties(properties);
        atomMapper.addAtom(atom);
        return HttpResult.ok(null);
    }

//    public List<Object> generateConfig(JSONObject obj) {
//        List<Object> _list = new ArrayList<Object>();
//        for (Map.Entry entry : obj.entrySet()) {
//            JSONObject item = (JSONObject) entry.getValue();
//            String key = (String) entry.getKey();
//            String type = (String) item.get("type");
//            JSONObject children = (JSONObject) item.get("children");
//            if ( type == "FieldsGroup" || type == "List") {
//                item.put("children", generateConfig(children));
//            }
//            _list.add(item);
//        }
//        return _list;
//    }

    @Override
    public PageResult getAtoms(PageRequest pageRequest, Map map) {
        return MybatisPageHelper.findPage(pageRequest, atomMapper, "getAtoms", map);
    }

    @Override
    public HttpResult delAtom(Long id) {
        atomMapper.delAtom(id);
        return HttpResult.ok(null);
    }

    @Override
    public Atom getById(Long id) {
        Atom atom = atomMapper.getById(id);
        Map properties = (Map) JSONObject.parse(atom.getProperties(), Feature.OrderedField);
        atom.setPropertyObj(properties);
        return atom;
    }
}
