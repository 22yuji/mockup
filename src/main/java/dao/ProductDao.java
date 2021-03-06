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
	private static final String SQL_SELECT_ID = "SELECT product_id FROM products WHERE product_id = ?";
	private static final String SQL_SELECT_ID_DETAIL = "SELECT p.product_id, p.name, p.price, c.name c_name, description " 
			+ "FROM products p JOIN categories c ON p.category_id = c.id " 
			+ "WHERE product_id = ?";
	private static final String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_INSERT = "INSERT INTO products (product_id, category_id, name, price, description) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? "
			+ "WHERE product_id = ?";
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
	
	public Product searchId(int proId) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ID)) {
            stmt.setInt(1, proId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                /*return new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"),
                		rs.getString("c_name"), rs.getString("description"));*/
            	return new Product(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
	
	public Product searchDetail(int proId) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ID_DETAIL)) {
            stmt.setInt(1, proId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"),
                		rs.getString("c_name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
	
	public Product destroyPro(int proId) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE)) {
			stmt.setInt(1, proId);
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
	
	public Product insertProduct(int proId, int cateId, String proName, int price, String desc) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT)) {
			stmt.setInt(1, proId);
			stmt.setInt(2, cateId);
			stmt.setString(3, proName);
			stmt.setInt(4, price);
			stmt.setString(5, desc);
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
	
	public Product updatePro(int proId, int cateId, String proName, int price, String desc) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE)) {
			stmt.setInt(1, proId);
			stmt.setString(2, proName);
			stmt.setInt(3, price);
			stmt.setInt(4, cateId);
			stmt.setString(5, desc);
			stmt.setInt(6, proId);
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}
}
