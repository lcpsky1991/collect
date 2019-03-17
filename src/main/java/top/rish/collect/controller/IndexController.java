package top.rish.collect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping
@Controller
public class IndexController {
    public Object list(@RequestParam String page,@RequestParam String limit){
        return null;
    }

}
