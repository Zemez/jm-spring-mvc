package com.javamentor.jm_spring_mvc.controller;

import com.javamentor.jm_spring_mvc.model.Role;
import com.javamentor.jm_spring_mvc.model.User;
import com.javamentor.jm_spring_mvc.service.RoleService;
import com.javamentor.jm_spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView user(ModelMap model) {
        if (!model.containsAttribute("user")) {
            User user;
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            if (username == null) {
                user = new User();
            } else if ((user = userService.find(username)) == null) {
                user = new User(username);
            }
            logger.info("user: " + user);
            model.addAttribute("user", user);
        }
        return new ModelAndView("user", model);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT})
    public RedirectView update(RedirectAttributes attributes, @ModelAttribute User user) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User authUser = userService.find(username);
        if (authUser.getId().equals(user.getId()) && authUser.getUsername().equals(user.getUsername())) {
            identifyRoles(user.getRoles());
            try {
                logger.info(user.toString());
                userService.update(user);
                attributes.addFlashAttribute("note", "User successful updated.");
            } catch (Exception e) {
                attributes.addFlashAttribute("error", e.getMessage());
            }
            attributes.addFlashAttribute("user", user);
        } else {
            attributes.addFlashAttribute("error", "Invalid user data");
        }
        return new RedirectView("/user");
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public RedirectView delete(RedirectAttributes attributes) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.find(username);
        if (user != null) {
            try {
                userService.delete(user.getId());
                attributes.addFlashAttribute("note", "User successful deleted.");
            } catch (Exception e) {
                attributes.addFlashAttribute("error", e.getMessage());
            }
        } else {
            attributes.addFlashAttribute("error", "Something going wrong.");
        }
        return new RedirectView("/");
    }

    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleService.find();
    }

    private void identifyRoles(List<Role> roles) {
        for (Role role : roles) {
            if (role.getId() == null) {
                role.setId(roleService.find(role.getName()).getId());
            }
        }
    }

}
