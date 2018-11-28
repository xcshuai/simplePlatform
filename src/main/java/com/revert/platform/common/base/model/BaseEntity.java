package com.revert.platform.common.base.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1879869989975645868L;

    private Long id;

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String orderBy;




}
