package com.javamentor.jm_spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RootController {

//    private static final Logger logger = Logger.getLogger(RootController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView root(ModelMap model) {
        return new ModelAndView("index", model);
    }

}
