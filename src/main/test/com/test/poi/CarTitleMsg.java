package com.test.poi;

import lombok.Data;

@Data
public class CarTitleMsg {

    private String polourl;
    private String msgTitle;
    private String alt = this.msgTitle;
    private String msgbody;


}
