package Service;

import Entity.User;

public interface UserService {
	User login(String username, String password);

	User getUserName(String username);

	void insert(User user);

	boolean register(String email, String password, String username);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);
	
	void setToken(String email, String token);
	
	boolean checkExistToken(String token);
	
	boolean updatePasswordWithToken(String token, String newPassword);
	
	User findById(Integer id);
	


}