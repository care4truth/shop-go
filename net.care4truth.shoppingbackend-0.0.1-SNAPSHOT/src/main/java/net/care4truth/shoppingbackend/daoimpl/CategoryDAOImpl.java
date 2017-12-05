package net.care4truth.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional // must be at class level as everything here is a transaction
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public static List<Category> categories = new ArrayList<Category>();
	
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		//return sessionFactory.getCurrentSession().get(List<Category>);
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		System.out.println(query.getResultList());
		return query.getResultList();
	}
	// Getting single category 
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
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

	//Update a single category
	@Override
	public boolean update(Category category) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
