package net.care4truth.shoppingbackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.care4truth.shoppingbackend.dao.ProductDAO;
import net.care4truth.shoppingbackend.dto.Category;
import net.care4truth.shoppingbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.care4truth.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	
	/*@Test
	public void testCRUDProduct() {
		product = new Product();
		product.setName("Micromax s6");
		product.setBrand("Micromax");
		product.setDescription("This is some description for micromax mobile phones");
		product.setUnitPrice(480);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while inserting into a new product!",true,productDAO.add(product));
		
		
		product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		
		assertEquals("Something went wrong while updating the existing record!",true, productDAO.update(product) );
		
		assertEquals("Something went wrong while deleting the existing record!",true, productDAO.delete(product));
		
		//list
		assertEquals("Something went wrong while fetching the list of products",7,productDAO.list().size());
	}*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",5,productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",3,productDAO.listActiveProductsByCategory(3).size());
		
		assertEquals("Something went wrong while fetching the list of products!",1,productDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",3,productDAO.getLatestActiveProducts(3).size());
	}
	
}
