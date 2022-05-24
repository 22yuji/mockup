package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Product;

public class ProductDao {
	Connection con;
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM users";
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public Product searchAll() {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("name"),
                		rs.getInt("price"), rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
	}
}
