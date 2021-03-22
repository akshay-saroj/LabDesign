package com.labdesign.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labdesign.pojo.Category;
import com.labdesign.pojo.ErrorResponse;
import com.labdesign.pojo.Item;
import com.labdesign.service.LabDesignCategoryService;

/**
 * Controller class for handling category and Item related APIs
 * @author akshaysaroj
 *
 */
@RestController
@RequestMapping("/category")
public class LabDesignCategoryController {

	@Autowired
	LabDesignCategoryService service;
	
	/**
     * Creates a category
     * Exceptions are handled in ControllerAdvisor.class
     * @param category : category to be created
     * @return - Error/Success response
     */
	@PostMapping(value="/", produces = "application/json")
	public ResponseEntity createCategory(@RequestBody @Valid Category category) {
		service.createCategory(category);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
     * Creates Item for a particular category
     * Exceptions are handled in ControllerAdvisor.class
     * @param item - item to be created
     * @param categoryId - category id for for the item
     * @return - Error/Success response
     */
	@PostMapping(value="/{categoryId}/item", produces = "application/json")
	public ResponseEntity createItemForCategory(@RequestBody Item item, @PathVariable("categoryId") Long categoryId ){
		service.createItemForCategory(item,categoryId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
     * Updates Item for a particular category
     * Exceptions are handled in ControllerAdvisor.class
     * @param item - item to be updated
     * @param categoryId - category id for for the item
     * @return - Error/Success response
     */
	@PatchMapping(value="{categoryId}/item/", produces = "application/json")
	public ResponseEntity updateItem(@RequestBody Item item, @PathVariable("categoryId") Long categoryId){
		service.updateItem(item, categoryId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
     * Retrieve Items for a particular category
     * Exceptions are handled in ControllerAdvisor.class
     * @param categoryId - category id to be retrieved
     * @return - All items response
     */
	@GetMapping(value="/{categoryId}", produces = "application/json")
	public ResponseEntity retrieveItemsForCategory(@PathVariable("categoryId") Long categoryId){
		return new ResponseEntity<>(service.retrieveItemsForCategory(categoryId), HttpStatus.OK);
	}
	
	/**
     * Retrieves all categories
     * Exceptions are handled in ControllerAdvisor.class
     * @return - All categories response
     */
	@GetMapping(value="/", produces = "application/json")
	public ResponseEntity retrieveAllCategories(){
		List<Category> list = service.retrieveAllCategories();
		return new ResponseEntity<>(service.retrieveAllCategories(), HttpStatus.OK);
	}
}
