package com.labdesign.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.labdesign.enumeration.ItemMaterial;
import com.labdesign.pojo.Category;
import com.labdesign.pojo.Item;

@Component
public class InputValidator {

	public static final String DESCRIPTION_MANDATORY_MESSAGE = "For material of type Others, description is mandatory";
	
	public void validateCreateCategoryInput(Category category) {
		if(null != category) {
			if(category.getMaterial() == ItemMaterial.O && StringUtils.isEmpty(category.getDescription())) {
				throw new IllegalArgumentException(DESCRIPTION_MANDATORY_MESSAGE);
			}
		}
	}
	
	public void validateCreateOrUpdateItemForCategory(Category category, Item item) {
		if(null != category.getMinWeight() && null != category.getMaxWeight()
				&& (item.getWeight() < category.getMinWeight() || item.getWeight() > category.getMaxWeight())) {
			throw new IllegalArgumentException("For category "+category.getName() +" weight should be in the range of "
					+ category.getMinWeight() + " - " + category.getMaxWeight());
		}
		if(null != category.getMinWeight() && item.getWeight() < category.getMinWeight()) {
			throw new IllegalArgumentException("For category "+category.getName() +" weight should be greater than or "
					+ "equal to "+category.getMinWeight());
		}
		if(null != category.getMaxWeight() && item.getWeight() > category.getMaxWeight()) {
			throw new IllegalArgumentException("For category "+category.getName() +" weight should be less than or "
					+ "equal to "+category.getMaxWeight());
		}
		if(category.getMaterial() != item.getMaterial()) {
			throw new IllegalArgumentException("Category and Item material should be same");
		}
		if(item.getMaterial() == ItemMaterial.O && StringUtils.isEmpty(item.getDescription())) {
			throw new IllegalArgumentException(DESCRIPTION_MANDATORY_MESSAGE);
		}
		if(null != category.getSize() && category.getSize() != item.getSize()) {
			throw new IllegalArgumentException("Category and Item Size should be same");
		}
	}
}
