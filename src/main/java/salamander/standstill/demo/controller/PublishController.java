package salamander.standstill.demo.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
