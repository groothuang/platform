package com.platform.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = "/")
class DefaultController {
    @RequestMapping(value = "/")
    public String index() {
        return "web/index";
    }
}
