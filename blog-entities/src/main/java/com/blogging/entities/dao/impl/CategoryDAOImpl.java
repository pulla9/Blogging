package com.blogging.entities.dao.impl;

import com.blogging.entities.Category;
import com.blogging.entities.CategoryDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

    @PersistenceContext
    private EntityManager em;
    String qString = "SELECT c from Category c where c.name in (:list)";

    public List<Category> getCategories(List<String> list) {
        Query query = em.createQuery(qString);
        try {
            query.setParameter("list", list);
            List<Category> categories = (List<Category>) query.getResultList();
            return categories;
        } catch (NoResultException nre) {

        }
        return null;
    }
}
