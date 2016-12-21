package com.blogging.entities.dao.impl;

import com.blogging.entities.Article;
import com.blogging.entities.User;
import com.blogging.entities.dao.UserDAO;
import com.blogging.entities.exceptions.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by pulla6 on 18/12/16.
 */
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final String GET_ALL_ARTICLES_BY_USER = "User.getArticlesByUser";
    private static final String GET_ALL_USERS = "User.getAllUsers";

    public void save(User user) {
        em.persist(user);
    }

    public User getUser(int id) {
        try {
            User user = (User) em.find(User.class, id);
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        Query query = em.createNamedQuery(GET_ALL_USERS);
        try {
            List<User> list = query.getResultList();
            return list;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Set<Article> getArticlesByUser(int userId) {
        Query query = em.createNamedQuery(GET_ALL_ARTICLES_BY_USER);
        query.setParameter("id", userId);
        try {
            User user = (User) query.getSingleResult();
            return user.getArticleSet();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String qString = "SELECT u from User u where u.email=:email";
        Query query = em.createQuery(qString);
        query.setParameter("email", email);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
