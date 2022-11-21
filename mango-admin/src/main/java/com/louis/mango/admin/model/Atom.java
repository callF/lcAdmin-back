package com.louis.mango.admin.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Map;

@Data
public class Atom {
    private Long id;
    private String type;
    private String description;
    @JsonIgnore
    private String properties;

    // 非数据库字段
    private Map propertyObj;
}
