package com.labdesign.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labdesign.pojo.Category;

@Repository
public interface LabDesignCategoryRepository extends JpaRepository<Category, Long>{
	
}
