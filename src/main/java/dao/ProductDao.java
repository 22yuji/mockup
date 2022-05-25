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
	
	private static final String SQL_SELECT_ALL = "SELECT p.product_id, p.name, p.price, c.name c_name FROM products p "
			+ "JOIN categories c ON p.category_id = c.id ORDER BY p.product_id";
	private static final String SQL_SELECT = "SELECT p.product_id, p.name, p.price, c.name c_name FROM products p "
			+ "JOIN categories c ON p.category_id = c.id "
			+ " WHERE p.name LIKE ? OR c.name LIKE ? ORDER BY p.product_id ";
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> searchAll() {
		List<Product> list = new ArrayList<Product>();
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Product p = new Product(rs.getInt("product_id"), rs.getString("name"),
                		rs.getInt("price"), rs.getString("c_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
	}
	
	public List<Product> searchKey(String keyWord) {
		List<Product> list = new ArrayList<Product>();
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT)) {
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Product p = new Product(rs.getInt("product_id"), rs.getString("name"),
                		rs.getInt("price"), rs.getString("c_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
	}
}
