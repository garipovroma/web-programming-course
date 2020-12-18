package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    public Long getLong(String str) {
        Long idValue = null;
        try {
            idValue = Long.parseLong(str);
        } catch (NumberFormatException e) {
            //
        }
        return idValue;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable String id, HttpSession httpSession) {
        Long idValue = getLong(id);
        Post post = postService.find(idValue);
        if (post == null) {
            putMessage(httpSession, "There is no such post");
            return "redirect:/";
        }
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new CommentForm());
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String post(@PathVariable String id, @Valid @ModelAttribute("commentForm") CommentForm commentForm,
                       BindingResult bindingResult,
                       HttpSession httpSession, Model model) {
        Long idValue = getLong(id);
        Post post = postService.find(idValue);
        if (post == null) {
            putMessage(httpSession, "There is no such post");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }
        if (getUser(httpSession) == null) {
            putMessage(httpSession, "Comments available only for logged in users");
            return "redirect:/";
        }
        Comment comment = new Comment();
        comment.setText(commentForm.getText());
        comment.setPost(post);
        comment.setUser(getUser(httpSession));
        postService.writeComment(post, comment);

        model.addAttribute("post", post);
        return "PostPage";
    }
}
