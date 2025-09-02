package vn.oistart.service.impl;

import vn.oistart.dao.CategoryDao;
import vn.oistart.dao.impl.CategoryDaoImpl;
import vn.oistart.model.Category;
import vn.oistart.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }

    @Override
    public List<Category> getAllByUser(int userId) {
        return categoryDao.getAllByUser(userId);
    }
}
