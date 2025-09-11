package Service.ServiceImpl;



import Dao.DaoImpl.UserDaoImpl;
import Dao.UserDao;
import Entity.User;
import Service.UserService;

public class UserServiceImpl implements UserService {
	UserDao UserDao = new UserDaoImpl();

	public User login(String username, String password) {
		User User = this.getUserName(username);
		if (User != null && password.equals(User.getPassword())) {
			return User;
		}
		return null;
	}

	public User getUserName(String username) {
		return UserDao.getUserName(username);
	}
	public User findById(Integer id) {
		return UserDao.findById(id);
	}

	
	@Override
	public boolean register(String username, String password, String email) {
		if (UserDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		UserDao.insert(new User(username, password,email));
		return true;
	}

	public boolean checkExistEmail(String email) {
		return UserDao.checkExistEmail(email);
	}

	public boolean checkExistUsername(String username) {
		return UserDao.checkExistUsername(username);
	}


	@Override
	public void insert(User user) {
		UserDao.insert(user);
	}
	
	
	
	@Override
	public void setToken(String email, String token) {
		UserDao.setToken(email, token);
	}
	
	
	
	@Override
	public boolean checkExistToken(String token) {
		return UserDao.checkExistToken( token);
	}
	
	@Override
	public boolean updatePasswordWithToken(String token, String newPassword) {
		return UserDao.updatePasswordWithToken(token, newPassword);
	}
	
	
	
}
