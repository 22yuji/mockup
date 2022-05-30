package service;

import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	public List<Product> searchAll() {
        try (Connection con = DbUtil.getConnection()) {
        	ProductDao productDao = new ProductDao(con);
            return productDao.searchAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public List<Product> searchKey(String keyWord) {
        try (Connection con = DbUtil.getConnection()) {
        	ProductDao productDao = new ProductDao(con);
            return productDao.searchKey(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public Product searchId(int proId) {
		try (Connection con = DbUtil.getConnection()) {
        	ProductDao productDao = new ProductDao(con);
            return productDao.searchId(proId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}
	
	public Product insertProduct(int proId, int cateId, String proName, int price, String desc) {
        try (Connection con = DbUtil.getConnection()) {
        	ProductDao productDao = new ProductDao(con);
            return productDao.insertProduct(proId, cateId, proName, price, desc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
