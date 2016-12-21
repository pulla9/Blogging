package com.blogging.restapp.services.impl;

import com.blogging.entities.Article;
import com.blogging.entities.dao.ArticleDAO;
import com.blogging.restapp.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    public void createService(Article article) {
        articleDAO.saveArticle(article, article.getUser().getId());
    }

    public List<Article> getAllArticles(int userId) {
        List<Article> list = articleDAO.getArticlesByUserId(userId);
        for (Article article : list) {
            article.setUser(null);
        }
        return list;
    }

    public List<Article> getAllArticlesByCategory(int catgeoryId) {
        List<Article> list = articleDAO.getArticlesByCategory(catgeoryId);
        for (Article article : list) {
            article.setUser(null);
        }
        return list;
    }

    public void deleteArticle(int id) {
        articleDAO.removeArticle(id);
    }
}
