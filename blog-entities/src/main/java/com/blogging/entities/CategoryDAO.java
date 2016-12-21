package com.blogging.entities;

import java.util.List;

/**
 * Created by pulla6 on 20/12/16.
 */
public interface CategoryDAO {
    public List<Category> getCategories(List<String> list);
}
