package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeForm;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String createNoticeGet(Model model, HttpSession httpSession) {
        if (model.getAttribute("user") == null) {
            putError(httpSession, "Page available only for logged-in users");
            return "redirect:";
        }
        model.addAttribute("noticeForm", new NoticeForm());
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String createNoticePost(@Valid @ModelAttribute("noticeForm") NoticeForm noticeForm,
                                   BindingResult bindingResult, HttpSession httpSession, Model model) {
        if (model.getAttribute("user") == null) {
            putError(httpSession, "Page available only for logged-in users");
            return "redirect:";
        }

        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        noticeService.createNotice(noticeForm);
        putMessage(httpSession, "Congrats, you have been created notice!");

        return "redirect:";
    }
}
