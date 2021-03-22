package com.labdesign.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.labdesign.enumeration.ItemMaterial;
import com.labdesign.enumeration.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column @NotNull(message = "Category Name is mandatory")
	private String name;
	@Column @Min(1)
	private Long minWeight;
	@Column @Min(1)
	private Long maxWeight;
	@Column
	private String description;
	@Column @NotNull(message = "Category material is mandatory")
	private ItemMaterial material;
	@Column 
	private Size size;
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "category")
	@JsonManagedReference
	private List<Item> items;
	
}
