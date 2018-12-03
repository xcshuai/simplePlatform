package com.test;

import com.alibaba.fastjson.JSONObject;
import com.revert.platform.RevertApplication;
import com.revert.platform.db.mapper.DBTableMapper;
import com.revert.platform.db.model.DBTable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={RevertApplication.class})
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class DBTableTest {

    @Autowired
    private DBTableMapper dbTableMapper;

    @org.junit.Test
    public void test(){
        List<DBTable> listDBTable = dbTableMapper.selectByProperties(null);
        List<DBTable> listDBTable2 = dbTableMapper.selectByProperties(null);
        List<DBTable> listDBTable3 = dbTableMapper.selectByProperties(null);
        List<DBTable> listDBTable4 = dbTableMapper.selectByProperties(null);
        List<DBTable> listDBTable5 = dbTableMapper.selectByProperties(null);
        System.out.println(JSONObject.toJSONString(listDBTable));
    }




}
