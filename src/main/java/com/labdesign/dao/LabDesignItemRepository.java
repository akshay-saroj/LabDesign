package com.labdesign.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labdesign.pojo.Item;

public interface LabDesignItemRepository extends JpaRepository<Item, Long>{

}
