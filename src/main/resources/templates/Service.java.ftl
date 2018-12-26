package ${packageVal}.service;

import com.revert.platform.common.base.service.BaseService;
import ${packageVal}.mapper.${className}Mapper;
import ${packageVal}.model.${className}Model;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ${className}Service extends BaseService<${className}Mapper,${className}Model> {
}