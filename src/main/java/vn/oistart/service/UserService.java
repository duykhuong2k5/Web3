package vn.oistart.service;

import vn.oistart.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    void insert(User user);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean existsByEmail(String email);
    void updatePasswordByEmail(String email, String newPassword);
}