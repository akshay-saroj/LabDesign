package com.labdesign.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.labdesign.dao.LabDesignCategoryRepository;
import com.labdesign.dao.LabDesignItemRepository;
import com.labdesign.pojo.Category;
import com.labdesign.pojo.Item;
import com.labdesign.util.InputValidator;
import com.labdesign.util.LabDesignUtils;

@Component
public class LabDesignCategoryServiceImpl implements LabDesignCategoryService {

	@Autowired
	LabDesignCategoryRepository categoryRepository;
	
	@Autowired
	LabDesignItemRepository itemRepository;
	
	@Autowired
	InputValidator validator;
	
	public static final String CATEGORY_NOT_FOUND_MESSAGE = "The Category with the ID is not found";
	public static final String ITEM_NOT_FOUND_MESSAGE = "The item with the ID is not found";
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void createCategory(Category category) {
		validator.validateCreateCategoryInput(category);
		categoryRepository.save(category);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void createItemForCategory(Item item, Long categoryId) {
		Optional<Category> opt = categoryRepository.findById(categoryId);
		if(opt.isPresent()) {
			Category category = opt.get();
			validator.validateCreateOrUpdateItemForCategory(category, item);
			item.setCategory(category);
			itemRepository.save(item);
		} else {
			throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void updateItem(Item item, Long categoryId) {
		Optional<Category> opt = categoryRepository.findById(categoryId);
		if(opt.isPresent()) {
			Category category = opt.get();
			Optional<Item> optItem = category.getItems().stream().filter((element) -> element.getId().equals(item.getId())).findFirst();
			if(optItem.isPresent()) {
				Item persistedItem = optItem.get();
				LabDesignUtils.mapItemForUpdate(item, persistedItem);
				validator.validateCreateOrUpdateItemForCategory(category, persistedItem);
				itemRepository.save(persistedItem);
			} else {
				throw new EntityNotFoundException(ITEM_NOT_FOUND_MESSAGE);
			}
		} else {
			throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public List<Item> retrieveItemsForCategory(Long categoryId) {
		Optional<Category> opt = categoryRepository.findById(categoryId);
		if(opt.isPresent()) {
			return opt.get().getItems();
		} else {
			throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public List<Category> retrieveAllCategories() {
		return categoryRepository.findAll();
	}
	
}
