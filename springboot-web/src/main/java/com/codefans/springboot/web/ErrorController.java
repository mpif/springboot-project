package com.codefans.springboot.web;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: codefans
 * @Date: 2022-07-15 9:29
 */
@Controller
public class ErrorController {

    @RequestMapping(path = "/error/{status}", produces = {"text/html"})
    public ModelAndView errorPage(@PathVariable Integer status){
        Map<String, Object> model = Collections.unmodifiableMap(new HashMap<>());
        switch (status){
            case 400:
            case 401:
            case 404:return new ModelAndView("/error/404", model);
            case 500:return new ModelAndView("/error/500", model);
            default:return new ModelAndView("/50x", model);
        }
    }

}
