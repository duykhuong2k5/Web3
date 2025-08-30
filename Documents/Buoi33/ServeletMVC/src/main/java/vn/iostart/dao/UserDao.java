package vn.iostart.dao;

import vn.iostart.model.User;
public interface UserDao {
    User login(String username, String password);
    void insert(User user);
    boolean checkExistUsername(String username);
}
