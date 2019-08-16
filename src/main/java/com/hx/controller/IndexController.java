package com.hx.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangch on 2019/7/23 09:58
 * description: 首页
 *
 * @since JDK 1.6
 */
@Api(tags = {"/"})
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "hello hx!";
    }

    @RequestMapping(value = "csrf", method = RequestMethod.GET)
    public String csrf() {
        return "";
    }

}
