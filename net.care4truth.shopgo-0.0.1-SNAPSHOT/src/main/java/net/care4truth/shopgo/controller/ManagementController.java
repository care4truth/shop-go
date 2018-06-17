package net.care4truth.shopgo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.care4truth.shopgo.util.FileUploadUtility;
import net.care4truth.shopgo.validator.ProductValidator;
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

	// adding logger Object
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product newProduct = new Product();
		// set few fields
		newProduct.setSupplierId(1);
		newProduct.setActive(true);

		mv.addObject("product", newProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		//fetchthe product from db 
		Product nProduct = productDAO.get(id);
		mv.addObject("product",nProduct);
		return mv;
	}

	// handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product modifiedProduct,
			BindingResult results, Model model, HttpServletRequest httpServletRequest) {

		if(modifiedProduct.getId() == 0) {
			new ProductValidator().validate(modifiedProduct, results);
		} else {
			if(!modifiedProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(modifiedProduct, results);
			}
		}
		

		// check if there are any errors
		if (results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission!");
			// should not return any redirect string instead we have to return
			// the same page
			return "page";
		}

		logger.info(modifiedProduct.toString());
		// create a new product record
		if(modifiedProduct.getId() == 0) {
			productDAO.add(modifiedProduct);
		} else {
			productDAO.update(modifiedProduct);
		}
		

		if (!modifiedProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(httpServletRequest, modifiedProduct.getFile(), modifiedProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);
		return (isActive) ? "You have successfully deactivated the product with id  " + product.getId() :
							"You have successfully activated the product with id " + product.getId();
	} 

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

}
