package vn.oistart.dao;

import vn.oistart.model.Category;
import java.util.List;

public interface CategoryDao {
 void insert(Category category);
 void edit(Category category);
 void delete(int id);
 Category get(int id);
 Category get(String name);
 List<Category> getAllByUser(int userId);
}
