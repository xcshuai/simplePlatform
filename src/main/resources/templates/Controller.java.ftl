package ${packageVal}.controller;

import com.revert.platform.common.base.model.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/${className}")
public class ${className}Controller {

    @RequestMapping(method = RequestMethod.GET)
    public WebResult test(){
        WebResult webResult = new WebResult();
        return webResult;
    }

}