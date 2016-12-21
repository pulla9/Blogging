package com.blogging.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
@ContextConfiguration(locations = "/applicationContext-entities-test.xml")
public class CategoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CategoryDAO categoryDAO;

    //@Test
    public void getCategoriesTest() {
        List<String> list = prepareCategories();
        List<Category> categories = categoryDAO.getCategories(list);
        for (Category category : categories)
            System.out.println(category.getName());
    }

    private List<String> prepareCategories() {
        List<String> list = new ArrayList<String>();
        Category c1 = new Category();
        c1.setName("Technology");
        Category c2 = new Category();
        c2.setName("Machine Learning");
        list.add(c1.getName());
        list.add(c2.getName());
        list.add("Deep Learning");
        return list;
    }
}
