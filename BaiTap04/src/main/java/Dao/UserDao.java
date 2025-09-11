package Dao;

import Entity.User;

public interface UserDao {
	User getUserName(String username);

	void insert(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);
	
	void setToken(String email, String token);
	
	boolean checkExistToken(String token);
	
	boolean updatePasswordWithToken(String token, String newPassword);
	
	User findById(Integer id);

}
