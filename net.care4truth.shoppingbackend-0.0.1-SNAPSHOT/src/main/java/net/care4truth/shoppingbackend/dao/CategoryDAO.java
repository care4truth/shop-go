package net.care4truth.shoppingbackend.dao;

import java.util.List;

import net.care4truth.shoppingbackend.dto.Category;

public interface CategoryDAO {
	List<Category> list();
	Category get(int id);
	boolean add(Category category);
}
