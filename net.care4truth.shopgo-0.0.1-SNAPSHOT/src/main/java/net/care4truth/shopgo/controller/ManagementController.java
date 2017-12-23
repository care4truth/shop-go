package net.care4truth.shopgo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.care4truth.shoppingbackend.dao.CategoryDAO;
import net.care4truth.shoppingbackend.dao.ProductDAO;
import net.care4truth.shoppingbackend.dto.Category;
import net.care4truth.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	//adding logger Object
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	
	@RequestMapping(value="/products" , method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required = false)String operation)	 {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title" , "Manage Products");
		
		Product newProduct = new Product();
		//set few fields
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		
		mv.addObject("product", newProduct);
		
		if(operation != null ) {
			if(operation.equals("product")){
				mv.addObject("message","Product submitted successfully");
			}
		}
		return mv;
	}
	//handling product submission
	@RequestMapping(value="/products" , method = RequestMethod.POST) 
	public String handleProductSubmission(@ModelAttribute("product") Product modifiedProduct) {
		
		logger.info(modifiedProduct.toString());
		//create a new product record
		productDAO.add(modifiedProduct);
		return "redirect:/manage/products?operation=product";
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	
}
