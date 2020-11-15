package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();
    private final UserService userService = new UserService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "Talks available only for logged in users");
            request.getSession().setAttribute("messageType", "error");
            throw new RedirectException("/index");
        }
    }
    //:TODO: correct validation

    private void createArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        articleService.validateArticle(request);
        User user = (User) request.getSession().getAttribute("user");
        Article article = new Article();
        article.setUserId(user.getId());
        article.setTitle((String) request.getParameter("title"));
        article.setText((String) request.getParameter("articleText"));
        articleService.create(article);
        request.getSession().setAttribute("message", "You are successfully created the article!");
        throw new RedirectException("/index");
    }
}
