package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

public class ArticleService {
    private ArticleRepositoryImpl articleRepository = new ArticleRepositoryImpl();
    public void create(Article article) {
        articleRepository.save(article);
    }
    public void validateArticle(HttpServletRequest request) {
        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        if (Strings.isNullOrEmpty(title)) {
            request.getSession().setAttribute("message", "Title cant be empty");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
        if (Strings.isNullOrEmpty(articleText)) {
            request.getSession().setAttribute("message", "Article cant be empty");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
        if (title.length() >= 400) {
            request.getSession().setAttribute("message", "Article title too large");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
        if (articleText.length() >= 400) {
            request.getSession().setAttribute("message", "Article too large");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
    }
}
