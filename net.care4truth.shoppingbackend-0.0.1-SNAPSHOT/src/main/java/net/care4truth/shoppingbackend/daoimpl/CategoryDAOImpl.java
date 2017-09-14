package net.care4truth.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dto.Category;

public class CategoryDAOImpl implements CategoryDAO {
	
	public static List<Category> categories = new ArrayList<Category>();
	
	static {
		Category category = new Category();
		// adding first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("Here is some description for television!");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		
		categories.add(category);
		
		//second category
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Here is some description for mobile!");
		category.setImageURL("CAT_2.png");
		category.setActive(true);
		
		categories.add(category);
		
		//third category
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Here is some description for laptop!");
		category.setImageURL("CAT_3.png");
		category.setActive(true);
		
		categories.add(category);	
	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
