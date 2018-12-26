<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageVal}.mapper.${className}Mapper">
    <resultMap id="BaseResultMap" type="${packageVal}.model.${className}Model">
<#if (oldColumns?exists) && (oldColumns?size>0)>
<#list oldColumns as column>
    <#if (column.columnName == "id")>
        <id column="${column.columnName}" jdbcType="${column.dataType}" property="${column.aliasName}" />
    <#else>
        <result column="${column.columnName}" jdbcType="${column.dataType}" property="${column.aliasName}" />
    </#if>
</#list>
</#if>
    </resultMap>
    <sql id="Base_Column_List">
<#if (oldColumns?exists) && (oldColumns?size>0)>
    <#assign count = oldColumns?size/>
    <#list oldColumns as column>
        <#assign count = count - 1/>
        ${column.columnName}<#if (count) gt 0>,</#if>
    </#list>
</#if>
    </sql>
    <sql id="Base_Column_List_Alis">
    <#assign count = oldColumns?size/>
    <#list oldColumns as column>
        <#assign count = count - 1/>
        ${className}.${column.columnName}<#if (count) gt 0>,</#if>
    </#list>
    </sql>
    <sql id="Base_Where_List_Alis">
    <#list oldColumns as column>
        <if test="${column.aliasName} != null">
            AND ${className}.${column.columnName} = ${r"#{"}${column.aliasName}${r",jdbcType="}${column.dataType}${r"}"}
        </if>
    </#list>
    </sql>
    <select id="selectByProperties" resultMap="BaseResultMap" parameterType="${packageVal}.model.${className}Model" >
        select <include refid="Base_Column_List_Alis"/>
        from ${tableName} ${className}
        WHERE 1=1
        <include refid="Base_Where_List_Alis"/>
    </select>
    <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where id = ${r"#{id,jdbcType=BIGINT}"}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
        delete from sys_t_user
        where id = ${r"#{id,jdbcType=BIGINT}"}
    </delete>
    <insert id="insert" parameterType="${packageVal}.model.${className}Model" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName} (
            <#assign count = oldColumns?size/>
            <#list oldColumns as column>
                <#assign count = count - 1/>
            ${column.columnName}<#if (count) gt 0>,</#if>
            </#list>
        )
        values (
            <#assign count = oldColumns?size/>
            <#list oldColumns as column>
                <#assign count = count - 1/>
            ${column.aliasName}<#if (count) gt 0>,</#if>
            </#list>
        )
    </insert>
    <insert id="insertSelective" parameterType="${packageVal}.model.${className}Model" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list oldColumns as column>
            <if test="${column.aliasName} != null">
                ${column.columnName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list oldColumns as column>
            <if test="${column.aliasName} != null">
                ${r"#{"}${column.aliasName}${r"},"}
            </if>
        </#list>
        </trim>
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="${packageVal}.model.${className}Model">
        update ${tableName}
        <set>
    <#list oldColumns as column>
        <#if (column.columnName != "id")>
            <if test="${column.aliasName} != null">
                ${column.columnName} = ${r"#{"}${column.aliasName}${r",jdbcType="}${column.dataType}${r"}"},
            </if>
        </#if>
    </#list>
        </set>
        where id = ${r"#{id,jdbcType=BIGINT}"}
    </update>
    <update id="updateByPrimaryKey" parameterType="${packageVal}.model.${className}Model">
        update ${tableName}
        set
        <#assign count = oldColumns?size/>
        <#list oldColumns as column>
            <#assign count = count - 1/>
        ${column.columnName} = ${r"#{"}${column.aliasName}${r",jdbcType="}${column.dataType}${r"}"}<#if (count) gt 0>,</#if>
        </#list>
        where id = ${r"#{id,jdbcType=BIGINT}"}
    </update>
</mapper>