package com.DATN.WebBanDienThoai.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	private UUID id;
	private String name;
	private String content;
	private String summary;
	private boolean isBestSell;
	private boolean isFalseSale;
	private double price;
	private double originalPrice;

	
	@OneToMany(mappedBy = "product")
	private Set<Comment> comments;
	@ManyToMany (mappedBy = "products")
	private Set<Category> categories;
	
	@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	@OneToMany(mappedBy = "product")
	private Set<Option> options;
	
	@OneToMany(mappedBy = "product")
	private Set<OptionDetail> optionDetails;
	
	
}
