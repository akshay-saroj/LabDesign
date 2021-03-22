package com.labdesign.util;

import org.apache.commons.lang3.StringUtils;

import com.labdesign.pojo.Item;

public class LabDesignUtils {

	public static void mapItemForUpdate(Item source, Item destination) {
		if(source != null) {
			if(StringUtils.isNoneEmpty(source.getName())) {
				destination.setName(source.getName());
			}
			if(null != source.getWeight()) {
				destination.setWeight(source.getWeight());
			}
			if(null != source.getMaterial()) {
				destination.setMaterial(source.getMaterial());
			}
			if(null != source.getDescription()) {
				destination.setDescription(source.getDescription());
			}
			if(null != source.getSize()) {
				destination.setSize(source.getSize());
			}
		}
	}
	
}
