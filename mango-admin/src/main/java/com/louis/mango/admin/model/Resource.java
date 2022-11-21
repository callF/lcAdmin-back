package com.louis.mango.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Map;

@Data
public class Resource {
    private Long id;
    private String code;
    private Long atomId;
    @JsonIgnore
    private String configObjStr;

    // 非数据库字段
    private String type;
    private Atom atom;
    private Map configObj;
}
