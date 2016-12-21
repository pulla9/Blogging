package com.blogging.entities;

import com.blogging.entities.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pulla6 on 18/12/16.
 */
@ContextConfiguration(locations = "/applicationContext-entities-test.xml")
public class ArticleTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private Article article;

    private static final int USER_ID = 1;

    @BeforeClass
    public void init() {
        Set<Category> categorySet = prepareCategorySet();
        article = new Article();
        article.setInfo("HOW Automation Can be Achieved for real world problems??");
        article.setCategorySet(categorySet);
    }

    //@Test(description = "Save an article for an user")
    public void test() {
        articleDAO.saveArticle(article, USER_ID);
    }

    //@Test(description = "Get all the articles associated with a user")
    public void testgetArticlesByUserId() {
        List<Article> articleList = articleDAO.getArticlesByUserId(USER_ID);
        Assert.assertEquals(articleList.size(), 1);
        /*if (articleList != null) {
            for (Article article : articleList) {
                System.out.println(article.getInfo());
            }
        }*/
    }

    //@Test(description = "Get all the articles belonging to a category")
    public void testgetArticlesByCategory() {
        List<Article> articles = articleDAO.getArticlesByCategory(1);
        Assert.assertEquals(articles.size(), 1);
        /*for (Article article : articles) {
            System.out.println(article.getInfo());
        }*/
    }

    //@Test(description = "Modify the category of a particular article")
    public void testUpdateArticles() {
        Category newCategory = new Category();
        newCategory.setName("Kormangla");
        articleDAO.updateArticleByCategory(1, -1, newCategory);
    }

    //@Test(description = "Remove the given Article")
    public void removeArticleTest() {
        articleDAO.removeArticle(1);
    }

    private Set<Category> prepareCategorySet() {
        Category c1 = new Category();
        c1.setName("AI");
        Category c2 = new Category();
        c2.setName("NLP");
        Set<Category> set = new HashSet<Category>();
        set.add(c1);
        set.add(c2);
        return set;
    }
}
