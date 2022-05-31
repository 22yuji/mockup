package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {
	Connection con;
	
	private static final String SQL_SELECT_LOGIN = "SELECT * FROM users WHERE login_id = ? AND password = ?";
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userCheck(String login_id, String pass) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_LOGIN)) {
            stmt.setString(1, login_id);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
	}

}
