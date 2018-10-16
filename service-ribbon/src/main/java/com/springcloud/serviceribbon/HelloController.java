package com.springcloud.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hi")
    @ResponseBody
    public String hi(@RequestParam String name) {
        return helloService.hiService(name) + "ribbon";
    }
}
