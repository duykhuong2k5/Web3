package vn.iostart.service.impl;


import vn.iostart.dao.UserDao;
import vn.iostart.dao.impl.UserDaoImpl;
import vn.iostart.model.User;
import vn.iostart.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public void register(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
}



