package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlesPage {
    UserService userService = new UserService();
    ArticleService articleService = new ArticleService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        checkLoggedInUser(request);
        putArticles(request, view);
    }
    private void changeArticleHiddenField(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        checkLoggedInUser(request);
        long userId = ((User) request.getSession().getAttribute("user")).getId();
        long articleId = (Long.parseLong(request.getParameter("articleId")));
        articleService.validateArticleCreator(userId, articleId);
        articleService.changeHiddenField(articleId);
    }
    private void checkLoggedInUser(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "This page is available only for logged-in users");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
    }
    private void putArticles(HttpServletRequest request, Map<String, Object> view) {
        List<Article> articleList = articleService.findAll();
        view.put("articles", articleList);
    }
}
