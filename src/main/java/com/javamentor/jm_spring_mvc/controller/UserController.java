package com.javamentor.jm_spring_mvc.controller;

import com.javamentor.jm_spring_mvc.model.User;
import com.javamentor.jm_spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {

//    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView user(ModelMap model, @RequestParam(required = false) Long id) {
        if (id != null && !model.containsAttribute("user")) {
            User user = userService.findUser(id);
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                model.addAttribute("error", "User not found.");
            }
        }
        return new ModelAndView("user", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("user");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView create(RedirectAttributes attributes,
                               @RequestParam String login, String password, String name, String email) {

        User user = new User(login, password, name, email);
        try {
            userService.saveUser(user);
            attributes.addFlashAttribute("note", "User successful created.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        attributes.addFlashAttribute("user", user);
        return new RedirectView("/user");
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public RedirectView read(RedirectAttributes attributes, @RequestParam(required = false) Long id) {
        if (id != null) {
            try {
                User user = userService.findUser(id);
                if (user != null) {
                    attributes.addFlashAttribute("user", user);
                    attributes.addAttribute("id", id);
                    return new RedirectView("/user");
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
            model.addAttribute("users", userService.findAllUsers());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return new ModelAndView("users", model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RedirectView update(RedirectAttributes attributes,
                               @RequestParam Long id, String login, String password, String name, String email) {

        User user = new User(id, login, password, name, email);
        try {
            userService.updateUser(user);
            attributes.addFlashAttribute("note", "User successful updated.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        attributes.addFlashAttribute("user", user);
        attributes.addAttribute("id", id);
        return new RedirectView("/user");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public RedirectView delete(RedirectAttributes attributes, @RequestParam Long id) {
        try {
            userService.deleteUser(id);
            attributes.addFlashAttribute("note", "User successful deleted.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/");
    }

}
