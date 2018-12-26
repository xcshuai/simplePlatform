package com.revert.platform.db.service;

import com.revert.platform.common.base.service.BaseService;
import com.revert.platform.db.mapper.DBTableMapper;
import com.revert.platform.db.model.Columns;
import com.revert.platform.db.model.DBTable;
import com.revert.platform.db.utils.DBCreateFileUtil;
import freemarker.template.Template;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DBTableService extends BaseService<DBTableMapper,DBTable> {

    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    @Value("${platform.java.target}")
    private String javaTagetPath;

    @Value("${platform.xml.target}")
    private String xmlTagetPath;

    @Value("${platform.dataSource.type}")
    private String sqlType;
    private static Map<String, String> templateFiles = new HashMap(){
        {
            put("mapper", "Mapper.java.ftl");
            put("model", "Model.java.ftl");
            put("service", "Service.java.ftl");
            put("controller", "Controller.java.ftl");
            put("mapper.impI", "Mapper.xml.ftl");
        }
    };

    public void generateCode(DBTable dbTable){
        Map<String, Map<String, String>> resultMap = DBCreateFileUtil.createFile(javaTagetPath, xmlTagetPath, dbTable.getPackageVal(),dbTable.getTableName(), sqlType);

        resultMap.forEach((key,val) -> {
            try{
                File tagetFile = null;
                // 渲染模板
                Writer tagetOut = null;
                String templateName = null;
                Map<String, Object> data = new HashMap<>();
                data.put("packageVal",dbTable.getPackageVal());
                data.put("oldColumns",setColumnJavaName(dbTable.getListColumns()));
                data.put("columns",setColumnJavaType(dbTable.getListColumns()));
                data.put("className",val.get("tableName"));
                data.put("tableName",dbTable.getTableName());

                tagetFile = new File(val.get("filePath"));
                tagetOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tagetFile)));
                templateName = templateFiles.get(key);
                Template template = freemarkerConfig.getConfiguration().getTemplate(templateName);
                template.process(data,tagetOut);
                tagetOut.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        });


        System.out.println();
    }

    public List<Columns> setColumnJavaName(List<Columns> listColumns){
        listColumns.forEach(item ->{
            item.setDataType(item.getDataType().toUpperCase());
            item.setAliasName(DBCreateFileUtil.toUpperCaseTableName(item.getColumnName(),2));
        });
        return listColumns;
    }

    public List<Columns> setColumnJavaType(List<Columns> listColumns){
        listColumns = listColumns.stream().filter(item -> !"id".equals(item.getColumnName()))
                .collect(Collectors.toList());
        listColumns.forEach(item ->{
            switch (item.getDataType().toLowerCase()){
                case "int":
                    item.setJavaType("Integer");
                    break;
                case "bigint":
                    item.setJavaType("Long");
                    break;
                case "datetime":
                case "timestamp":
                    item.setJavaType("Date");
                    break;
                default:
                    item.setJavaType("String");
                    break;
            }
        });
        return listColumns;
    }


}
