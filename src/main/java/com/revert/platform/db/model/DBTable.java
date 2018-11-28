package com.revert.platform.db.model;

import com.revert.platform.common.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 当前连接数据库表
 */
public class DBTable extends BaseEntity {

    @Getter
    @Setter
    private String tableName;

    @Getter
    @Setter
    private String tableRows;

    @Getter
    @Setter
    private String tableComment;

    @Getter
    @Setter
    private Date createTime;


}
