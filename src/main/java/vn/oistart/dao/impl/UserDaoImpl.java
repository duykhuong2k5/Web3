package vn.oistart.dao.impl;

import vn.oistart.connection.DBConnection;
import vn.oistart.dao.UserDao;
import vn.oistart.model.User;

import java.sql.*;

public class UserDaoImpl extends DBConnection implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
 @Override
 public User get(String username) {
     String sql = "SELECT * FROM user WHERE username=?";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, username);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
             User u = new User();
             u.setId(rs.getInt("id"));
             u.setEmail(rs.getString("email"));
             u.setUserName(rs.getString("username"));
             u.setFullName(rs.getString("fullname"));
             u.setPassWord(rs.getString("password"));
             u.setAvatar(rs.getString("avatar"));
             u.setRoleid(rs.getInt("roleid"));
             u.setPhone(rs.getString("phone"));
             u.setCreatedDate(rs.getDate("createddate"));
             return u;
         }
     } catch (Exception e) { e.printStackTrace(); }
     return null;
 }

 @Override
 public void insert(User user) {
     String sql = "INSERT INTO user(email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, user.getEmail());
         ps.setString(2, user.getUserName());
         ps.setString(3, user.getFullName());
         ps.setString(4, user.getPassWord());
         ps.setString(5, user.getAvatar());
         ps.setInt(6, user.getRoleid());
         ps.setString(7, user.getPhone());
         ps.setDate(8, user.getCreatedDate());
         ps.executeUpdate();
     } catch (Exception e) { e.printStackTrace(); }
 }

 @Override
 public boolean checkExistEmail(String email) {
     String sql = "SELECT id FROM user WHERE email=?";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, email);
         return ps.executeQuery().next();
     } catch (Exception e) { return false; }
 }

 @Override
 public boolean checkExistUsername(String username) {
     String sql = "SELECT id FROM user WHERE username=?";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, username);
         return ps.executeQuery().next();
     } catch (Exception e) { return false; }
 }

 @Override
 public boolean checkExistPhone(String phone) {
     String sql = "SELECT id FROM user WHERE phone=?";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, phone);
         return ps.executeQuery().next();
     } catch (Exception e) { return false; }
 }
 public User findByEmail(String email) {
	    String sql = "SELECT * FROM user WHERE email = ?"; // sửa lại 'user' thay vì 'users'
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email.trim());
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new User(
	                rs.getString("email"),
	                rs.getString("username"),
	                rs.getString("fullname"),
	                rs.getString("password"),
	                rs.getString("avatar"),
	                rs.getInt("roleid"),
	                rs.getString("phone"),
	                rs.getDate("createddate")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
}
 @Override
 public void updatePassword(String email, String newPassword) {
     String sql = "UPDATE user SET password=? WHERE email=?";
     try (Connection conn = new DBConnection().getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, newPassword);
         ps.setString(2, email.trim());
         int rows = ps.executeUpdate();
         System.out.println("Rows updated: " + rows);
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}

