package service;

import java.sql.Connection;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class LoginService {
	public User userCheck(String login_id, String pass) {
        try (Connection con = DbUtil.getConnection()) {
        	UserDao userDao = new UserDao(con);
            return userDao.userCheck(login_id, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
