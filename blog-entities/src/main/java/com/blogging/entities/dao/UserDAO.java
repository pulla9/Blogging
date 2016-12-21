package com.blogging.entities.dao;

import com.blogging.entities.Article;
import com.blogging.entities.User;
import com.blogging.entities.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * Created by pulla6 on 18/12/16.
 */
public interface UserDAO {
    public void save(User user);

    public User getUser(int id);

    public List<User> getAllUsers();

    public Set<Article> getArticlesByUser(int userId);

    public User getUserByEmail(String email);
}
