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
}
