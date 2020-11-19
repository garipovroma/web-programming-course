package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/** @noinspection unused*/
public class IndexPage {
    ArticleService articleService = new ArticleService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putArticles(request, view);
        putMessage(request, view);
    }

    private void putArticles(HttpServletRequest request, Map<String, Object> view) {
        List<Article> articleList = articleService.findAll();
        view.put("articles", articleList);
    }
    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
        String messageType = (String) request.getSession().getAttribute("messageType");
        if (!Strings.isNullOrEmpty(messageType)) {
            view.put("messageType", messageType);
            request.getSession().removeAttribute("messageType");
        }
    }
}
