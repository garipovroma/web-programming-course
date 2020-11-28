package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.UserStatusForm;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model, HttpSession httpSession) {
        if (model.getAttribute("user") == null) {
            putError(httpSession, "Page available only for logged-in users");
            return "redirect:/";
        }
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @GetMapping("/users/{id}")
    public String user(Model model, @PathVariable String id) {

        Long idValue = null;
        try {
            idValue = Long.parseLong(id);
        } catch (NumberFormatException e) {
            //
        }
        User foundUser = userService.findById(idValue);
        model.addAttribute("user", foundUser);
        return "UserPage";
    }

    @GetMapping("/users/setStatus")
    public String setStatusGet(Model model, HttpSession httpSession) {
        if (model.getAttribute("user") == null) {
            putError(httpSession, "Page available only for logged-in users");
            return "redirect:/";
        }
        model.addAttribute("userStatusForm", new UserStatusForm());
        return "UsersPage";
    }

    @PostMapping("/users/setStatus")
    public String setStatusPost(@Valid @ModelAttribute("userStatusForm") UserStatusForm userStatusForm,
                                BindingResult bindingResult,
                                HttpSession httpSession, Model model) {
        if (model.getAttribute("user") == null) {
            putError(httpSession, "Page available only for logged-in users");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            return "UsersPage";
        }
        User user = getUser(httpSession);
        if (user.isDisabled()) {
            putError(httpSession, "Your account was banned");
            return "redirect:/";
        }
        if (!userService.validateForm((User) model.getAttribute("user"), userStatusForm)) {
            putError(httpSession, "You cant ban your account");
            model.addAttribute("users", userService.findAll());
            return "UsersPage";
        }
        userService.updateDisabled(userStatusForm);
        putMessage(httpSession, "You have changed user status");
        model.addAttribute("users", userService.findAll());
        return "redirect:/users/all";
    }
}
