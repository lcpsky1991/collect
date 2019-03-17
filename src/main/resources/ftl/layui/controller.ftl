package ${package_name}.controller;
import ${package_name}.${service_package}.I${entity_name}Service;
import ${package_name}.${entity_package}.${entity_name};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

/*
    ${entity_name}控制层
    @deprecated ${table_comment}
    @author ${author}
    @date ${date}
 */
@RequestMapping("/${entity_name}")
public class ${entity_name}Controller {

    @Autowired
    private I${entity_name}Service ${entity_name?uncap_first}Service;
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Integer page,@RequestParam Integer limit){
        ${entity_name} ${entity_name?uncap_first}List = ${entity_name?uncap_first}Service.selectPageList();
        return ${entity_name?uncap_first}List;
    }
    @RequestMapping("/add")
    public Object add(){
        return null;
    }



}
