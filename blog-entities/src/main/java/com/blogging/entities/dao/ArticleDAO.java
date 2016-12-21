package com.blogging.entities.dao;

import com.blogging.entities.Article;
import com.blogging.entities.Category;

import java.util.List;

/**
 * Created by pulla6 on 18/12/16.
 */
public interface ArticleDAO {
    public void saveArticle(Article article, int userId);

    public List<Article> getArticlesByUserId(int userId);

    public List<Article> getArticlesByCategory(int categoryId);

    public void updateArticleByCategory(int articleId, int oldCategoryId, Category newCategory);

    public void removeArticle(int articleId);

}