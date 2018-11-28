package com.revert.platform.common.base.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private Long id;


    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String orderBy;




}
