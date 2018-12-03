package com.revert.platform.user.model;

import com.revert.platform.common.base.model.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {
    private String name;

    private String account;

    private String password;

    private Integer deleted;

    private Integer status;
}