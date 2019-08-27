package com.javamentor.jm_spring_mvc.controller;

import com.javamentor.jm_spring_mvc.model.Role;
import com.javamentor.jm_spring_mvc.model.User;
import com.javamentor.jm_spring_mvc.service.RoleService;
import com.javamentor.jm_spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(ModelMap model) {
        model.addAttribute("user", new User());
        return new ModelAndView("user", model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView signup(RedirectAttributes attributes, @ModelAttribute User user) {
        identifyRoles(user.getRoles());
        logger.info(user.toString());
        try {
            userService.save(user);
            attributes.addFlashAttribute("note", "User successful created.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/user");
    }

    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleService.find();
    }

    // проставить id в роли
    private void identifyRoles(List<Role> roles) {
        for (Role role : roles) {
            if (role.getId() == null) {
                role.setId(roleService.find(role.getName()).getId());
            }
        }
    }

}
