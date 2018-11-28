package com.revert.platform.user.controller;

import com.revert.platform.common.base.model.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public WebResult test(){
        WebResult webResult = new WebResult();
        System.out.println(1/0);
        return webResult;
    }

}
