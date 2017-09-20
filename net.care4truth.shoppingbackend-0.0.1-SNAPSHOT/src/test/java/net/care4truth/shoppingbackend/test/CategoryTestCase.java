package net.care4truth.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.care4truth.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/* @Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("Here is some description for Laptop!");
		category.setImageURL("CAT_3.png");

		assertEquals("successfully added a category Inside a table!", true,categoryDAO.add(category));

	}*/
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(1);
		assertEquals("Successfully fetched a category Inside a table!", "Television", category.getName());
	}*/
	/*@Test
	public void testUpdateCategory() {
	category = categoryDAO.get(1);
		category.setName("T.V");
		assertEquals("Successfully update a category Inside a table!", true, categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(1);
		assertEquals("Successfully deleted a category Inside a table!", true, categoryDAO.delete(category));
	}*/

	/*@Test
	public void testListCategory() {
		assertEquals("Successfully listed categories Inside a table!", 2, categoryDAO.list().size());
	}
	*/
	
	@Test
	public void testCRUDCategory() {
		category = new Category();
		Category category = new Category();
		category.setName("Laptop");
		category.setDescription("Here is some description for Laptop!");
		category.setImageURL("CAT_3.png");
		
		assertEquals("successfully added a category inside the table!",true, categoryDAO.add(category) );
		
		//second category
		category = new Category();
		
		category.setName("Television");
		category.setDescription("Here is some description for Television!");
		category.setImageURL("CAT_1.png");
		
		assertEquals("successfully added a category inside the table!",true,categoryDAO.add(category));
		
		//fetching and update
		category = categoryDAO.get(2);
		category.setName("T.V");
		assertEquals("Successfully update a category Inside a table!", true, categoryDAO.update(category));
		
		assertEquals("Successfully deleted a category Inside a table!", true, categoryDAO.delete(category));
		
		assertEquals("Successfully listed categories Inside a table!", 1, categoryDAO.list().size());
	}
	
}
