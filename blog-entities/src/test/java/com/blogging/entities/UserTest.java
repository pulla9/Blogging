package com.blogging.entities;

import com.blogging.entities.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by pulla6 on 18/12/16.
 */
@ContextConfiguration(locations = "/applicationContext-entities-test.xml")
public class UserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDAO userDAO;

    private User user;

    private static final int USER_ID = 1;

    @BeforeClass
    private void init() {
        user = new User();
        user.setName("Sathwik");
        user.setAge(24);
        user.setPassword("hello");
        user.setEmail("et@gmail.com");
        user.setMobile("8124011220");
    }

    //@Test(description = "register an user")
    public void testUserRegistration() {
        userDAO.save(user);
        Assert.assertEquals(user.getId(), 1);
    }

    //@Test(description = "Get user profile of a particular user")
    public void testGetUserProfile() {
        User actualUser = userDAO.getUser(1);
        Assert.assertEquals(actualUser.getName(), "Sathwik");
    }

    //@Test
    public void getArticlesByUserTest() {
        Set<Article> articleSet = userDAO.getArticlesByUser(USER_ID);
        for (Article article : articleSet) {
            System.out.println(article.getInfo());
        }
    }

    //@Test(description = "Get all the users")
    public void getAllUsersTest() {
        List<User> list = userDAO.getAllUsers();
        Assert.assertEquals(list.size(), 1);
    }
}
