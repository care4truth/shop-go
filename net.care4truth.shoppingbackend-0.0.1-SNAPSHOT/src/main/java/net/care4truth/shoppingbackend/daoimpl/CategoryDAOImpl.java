package net.care4truth.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
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
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Here is some description for mobile!");
		category.setImageURL("CAT_2.png");
		category.setActive(true);
		
		categories.add(category);
		
		//third category
		category = new Category();
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
		return this.categories;
	}
	
	@Override
	public Category get(int id) {
		//enhanced for loop 
		for(Category category : categories) {
			if (category.getId() == id) {
				return category; 
			}
		}
		
		return null;
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
