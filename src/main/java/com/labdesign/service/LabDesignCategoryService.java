package com.labdesign.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labdesign.pojo.Category;
import com.labdesign.pojo.Item;

@Service
public interface LabDesignCategoryService {

	public void createCategory(Category category);
	public void createItemForCategory(Item item, Long categoryId);
	public void updateItem(Item item, Long categoryId);
	public List<Item> retrieveItemsForCategory(Long categoryId);
	public List<Category> retrieveAllCategories();
}
