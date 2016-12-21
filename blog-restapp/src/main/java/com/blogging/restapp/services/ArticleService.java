package com.blogging.restapp.services;

import com.blogging.entities.Article;

import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
public interface ArticleService {
    public void createService(Article article);

    public List<Article> getAllArticles(int userId);

    public List<Article> getAllArticlesByCategory(int catgeoryId);

    public void deleteArticle(int id);
}
