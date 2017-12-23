package net.care4truth.shopgo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dto.Category;
import net.care4truth.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@RequestMapping(value="/products" , method = RequestMethod.GET)
	public ModelAndView showManageProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title" , "Manage Products");
		
		Product product = new Product();
		//set few fields
		product.setSupplierId(1);
		product.setActive(true);
		
		mv.addObject("product", product);
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDao.list();
	}
}
