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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView user(ModelMap model, @RequestParam(required = false) Long id) {
        User user;
        if (id == null) {
            if (model.containsAttribute("user")) {
                return new ModelAndView("user", model);
            }
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            user = userService.find(username);
        } else {
            user = userService.find(id);
        }

        if (user == null) {
            RedirectAttributes attributes = new RedirectAttributesModelMap();
            attributes.addFlashAttribute("error", "User not found.");
            return new ModelAndView("redirect:/");
        }
        model.addAttribute(user);
        return new ModelAndView("user", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model) {
        model.addAttribute("user", new User());
        return new ModelAndView("user", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView create(RedirectAttributes attributes, @ModelAttribute User user) {
        try {
            userService.save(user);
            attributes.addFlashAttribute("message", "User successful created.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        attributes.addFlashAttribute(user);
        return new RedirectView("/admin");
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public RedirectView read(RedirectAttributes attributes, @RequestParam(required = false) Long id) {
        if (id != null) {
            try {
                User user = userService.find(id);
                if (user != null) {
                    attributes.addFlashAttribute(user);
                    attributes.addAttribute(id);
                    return new RedirectView("/admin");
                }
                attributes.addFlashAttribute("error", "User not found.");
            } catch (Exception e) {
                attributes.addFlashAttribute("error", e.getMessage());
            }
        }
        return new RedirectView("/");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView all(ModelMap model) {
        try {
            model.addAttribute("users", userService.find());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return new ModelAndView("users", model);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT})
    public RedirectView update(RedirectAttributes attributes, @ModelAttribute User user) {
        identifyRoles(user.getRoles());
        try {
            userService.update(user);
            attributes.addFlashAttribute("message", "User successful updated.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        attributes.addFlashAttribute(user);
        attributes.addAttribute("id", user.getId());
        return new RedirectView("/admin");
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public RedirectView delete(RedirectAttributes attributes, @RequestParam Long id) {
        try {
            userService.delete(id);
            attributes.addFlashAttribute("message", "User successful deleted.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
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
