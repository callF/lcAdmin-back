package com.louis.mango.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Page {
    private Long id;
    private String name;
    private String path;
    @JsonIgnore
    private String cListStr;
    // 非数据库字段
    @JsonProperty("cList")
    private List<Long> cList;
    private List<Resource> resourceList;
}
