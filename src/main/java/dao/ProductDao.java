package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	Connection con;
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM products";
	private static final String SQL_SELECT_CATEGORYNAME = "SELECT name FROM categories WHERE id = ?";
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> searchAll() {
		List<Product> list = new ArrayList<Product>();
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Product p = new Product(rs.getInt("product_id"), rs.getString("name"),
                		rs.getInt("price"), rs.getInt("category_id"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
	}
}
