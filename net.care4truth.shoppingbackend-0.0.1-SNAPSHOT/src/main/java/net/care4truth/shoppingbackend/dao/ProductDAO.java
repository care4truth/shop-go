package net.care4truth.shoppingbackend.dao;

import java.util.List;
import net.care4truth.shoppingbackend.dto.Product;

public interface ProductDAO {
	List<Product> list();
	Product get(int id);
	
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	//business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
}
