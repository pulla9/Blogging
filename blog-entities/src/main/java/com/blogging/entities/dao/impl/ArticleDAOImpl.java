package com.blogging.entities.dao.impl;

import com.blogging.entities.Article;
import com.blogging.entities.Category;
import com.blogging.entities.CategoryDAO;
import com.blogging.entities.User;
import com.blogging.entities.dao.ArticleDAO;
import com.blogging.entities.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pulla6 on 18/12/16.
 */
@Transactional
public class ArticleDAOImpl implements ArticleDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private static final String GET_ALL_ARTICLES_BY_USERID = "Article.getArticlesByUserId";

    private static final String GET_ALL_ARTICLES_BY_CATEGORY = "Article.getArticlesByCategory";

    public void saveArticle(Article article, int userId) {
        filterCategories(article);
        User user = userDAO.getUser(userId);
        article.setUser(user);
        em.merge(article);
    }

    public List<Article> getArticlesByUserId(int userId) {
        User user = new User();
        user.setId(userId);
        Query query = em.createNamedQuery(GET_ALL_ARTICLES_BY_USERID);
        query.setParameter("user", user);
        List<Article> list = (List<Article>) query.getResultList();
        return list;
    }

    public List<Article> getArticlesByCategory(int categoryId) {
        Query query = em.createNamedQuery(GET_ALL_ARTICLES_BY_CATEGORY);
        query.setParameter("cat", categoryId);
        List<Article> list = (List<Article>) query.getResultList();
        return list;
    }

    public void updateArticleByCategory(int articleId, int oldCategoryId, Category newCategory) {
        Article article = em.find(Article.class, articleId);
        Set<Category> categories = article.getCategorySet();
        Category oldCategory = null;
        int flag = 0;
        for (Category c : categories)
            if (oldCategoryId == c.getId()) {
                if (c.getName().equals(newCategory.getName())) {
                    flag = 1;
                    break;
                } else {
                    flag = 2;
                    oldCategory = c;
                    break;
                }
            }
        if (flag == 0) {
            categories.add(newCategory);
            em.merge(article);
        } else if (flag == 2) {
            categories.remove(oldCategory);
            categories.add(newCategory);
            em.merge(article);
        }
    }

    public void removeArticle(int articleId) {
        Article article = em.find(Article.class, articleId);
        em.remove(article);
    }

    private void filterCategories(Article article) {
        List<String> list1 = new ArrayList<String>();
        Set<Category> newCategories = article.getCategorySet();
        Set<Category> finalCategories = new HashSet<Category>();
        for (Category category : newCategories)
            list1.add(category.getName());
        List<Category> existingCategories = categoryDAO.getCategories(list1);
        for (Category newCategory : newCategories) {
            Category temp = null;
            int flag = 0;
            for (Category existingCategory : existingCategories) {
                if (existingCategory.getName().equals(newCategory.getName())) {
                    temp = existingCategory;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                finalCategories.add(newCategory);
            else
                finalCategories.add(temp);
        }
        article.setCategorySet(finalCategories);
    }
}