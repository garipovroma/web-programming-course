package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    public List<Article> findAll();
    public void save(Article article);
    public Article find(long id);
}
