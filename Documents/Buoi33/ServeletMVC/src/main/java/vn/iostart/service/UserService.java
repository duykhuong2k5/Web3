package vn.iostart.service;

import vn.iostart.model.User;

public interface UserService {
    User login(String username, String password);
    void register(User user);
    boolean checkExistUsername(String username);
}

