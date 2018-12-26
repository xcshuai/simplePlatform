package ${packageVal}.model;

import com.revert.platform.common.base.model.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ${className}Model extends BaseEntity {

<#if (columns?exists) && (columns?size>0)>
    <#list columns as column>
        <#if (column.javaType == "Date")>
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        </#if>
        private ${column.javaType} ${column.columnName};

    </#list>
</#if>

}