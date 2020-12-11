package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.PostService;

@Controller
public class PostsPage extends Page {
    @GetMapping("/posts")
    public String posts() {
        return "PostsPage";
    }
}
