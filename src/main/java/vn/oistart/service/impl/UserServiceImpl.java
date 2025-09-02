package vn.oistart.service.impl;



import vn.oistart.dao.UserDao;
import vn.oistart.dao.impl.UserDaoImpl;
import vn.oistart.model.User;
import vn.oistart.service.UserService;

import java.sql.Date;

public class UserServiceImpl implements UserService {
 UserDao userDao = new UserDaoImpl();

 @Override
 public User login(String username, String password) {
     User u = userDao.get(username);
     if (u != null && password.equals(u.getPassWord())) return u;
     return null;
 }

 @Override
 public User get(String username) { return userDao.get(username); }

 @Override
 public void insert(User user) { userDao.insert(user); }

 @Override
 public boolean register(String username, String password, String email, String fullname, String phone) {
     if (userDao.checkExistUsername(username)) return false;
     long millis = System.currentTimeMillis();
     java.sql.Date date = new Date(millis);
     userDao.insert(new User(email, username, fullname, password, null, 3, phone, date));
     return true;
 }

 @Override
 public boolean checkExistEmail(String email) { return userDao.checkExistEmail(email); }
 @Override
 public boolean checkExistUsername(String username) { return userDao.checkExistUsername(username); }
 @Override
 public boolean checkExistPhone(String phone) { return userDao.checkExistPhone(phone); }
 @Override
 public boolean existsByEmail(String email) {
     return userDao.findByEmail(email) != null;
 }

 @Override
 public void updatePasswordByEmail(String email, String newPassword) {
	 userDao.updatePassword(email, newPassword);
 }
}
