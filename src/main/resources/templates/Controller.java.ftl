package ${packageVal}.controller;

import com.revert.platform.common.base.model.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import ${packageVal}.service.${className}Service;
import ${packageVal}.model.${className}Model;

@RestController
@RequestMapping("api/v1/${lowerClassName}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${lowerClassName}Service;

    @RequestMapping(method = RequestMethod.GET)
    public WebResult findByPage(${className}Model vo){
        WebResult webResult = new WebResult();
        webResult.data(${lowerClassName}Service.selectByPage(vo));
        return webResult;
    }

}