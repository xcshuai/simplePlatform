package com.test.poi;

import lombok.Data;

import java.util.List;

@Data
public class CarTitle {

    private String hotname;
    private String hottitle;
    private String hotdetail;
    private List<CarTitleMsg> msg;

}
