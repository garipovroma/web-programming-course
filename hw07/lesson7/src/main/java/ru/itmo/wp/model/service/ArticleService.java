package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ArticleService {
    private ArticleRepositoryImpl articleRepository = new ArticleRepositoryImpl();
    public void create(Article article) {
        articleRepository.save(article);
    }
    public void validateArticleAndUser(HttpServletRequest request, User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("This page is available on for logged-in users");
        }
        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Article title can't be empty");
        }
        if (Strings.isNullOrEmpty(articleText)) {
            throw new ValidationException("Article text can't be empty");
        }
        if (title.length() >= 400) {
            throw new ValidationException("Article title is too large");
        }
        if (articleText.length() >= 1500) {
            throw new ValidationException("Article text is too large");
        }
    }
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void changeHiddenField(long articleId) {
        Article article = articleRepository.find(articleId);
        Article result = articleRepository.setHiddenField(articleId, (!article.isHidden()));
    }

    public void validateArticleCreator(long userId, long articleId) throws ValidationException {
        Article article = articleRepository.find(articleId);
        if (article.getUserId() != userId) {
            throw new ValidationException("Current user isn't author of this article");
        }

    }
}
