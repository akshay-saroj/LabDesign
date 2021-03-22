package com.labdesign.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "table_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column @NotNull
	private String name;
	@Column 
	private Long weight;
	@Column
	private String description;
	@Column 
	private Size size;
	@Column @NotNull
	private ItemMaterial material;
	@ManyToOne 
	@JsonBackReference
	private Category category;
}
