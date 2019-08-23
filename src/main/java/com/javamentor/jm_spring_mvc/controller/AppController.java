package com.javamentor.jm_spring_mvc.controller;

import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class AppController {

    private static final Logger logger = Logger.getLogger(AppController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(ModelMap model) {
        logger.info("Main controller passed");
        return new ModelAndView("index", model);
    }

}
