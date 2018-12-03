package com.test.poi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarDetail implements Serializable {

    private String parseFlag = "1";
    private String mainname;
    private String price;
    private String type;
    private Integer item;
    private String polourl;
    private String alt;
    private String msgTitle;
    private String msgbody;
    private List<CarTitle> title;

}
