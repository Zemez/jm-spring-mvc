package com.javamentor.jm_spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class AppController {

    private static Logger logger = Logger.getLogger(AppController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public String app() {
        logger.info("Main controller passed");
        return "index";
    }
}
