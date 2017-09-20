package net.care4truth.shoppingbackend.daoimpl;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.care4truth.shoppingbackend.dao.ProductDAO;
import net.care4truth.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//public static List<Product> products = new ArrayList<Product>();
	
	@Override
	public List<Product> list() {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM Product" , Product.class).getResultList();	
	}

	/*
	 * (non-Javadoc)
	 * @see net.care4truth.shoppingbackend.dao.ProductDAO#get(int)
	 * Single Product 
	 */
	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(Product product) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(product);
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(product);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/*
	 * UPDATE 
	 * @see net.care4truth.shoppingbackend.dao.ProductDAO#delete(net.care4truth.shoppingbackend.dto.Product)
	 */
	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(product);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product where active = :active";
		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("active", true)
							.getResultList();
		
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryID) {
		String selectActiveProductsByCategory = "FROM Product where active = :active and categoryID = :categoryID";
		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory,Product.class)
						.setParameter("active", true)
							.setParameter("categoryID", categoryID)
								.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product where active = :active ORDER BY id",Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
								.setMaxResults(count)
									.getResultList();
	}

}
