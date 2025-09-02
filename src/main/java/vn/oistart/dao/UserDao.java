package vn.oistart.dao;

import vn.oistart.model.User;

public interface UserDao {
 User get(String username);
 void insert(User user);
 boolean checkExistEmail(String email);
 boolean checkExistUsername(String username);
 boolean checkExistPhone(String phone);
 User findByEmail(String email);
 void updatePassword(String email, String newPassword);
}

