package com.DATN.WebBanDienThoai.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DATN.WebBanDienThoai.entity.Category;
import com.DATN.WebBanDienThoai.payload.CategoryDTO;
import com.DATN.WebBanDienThoai.repository.CategoryRepository;
import com.DATN.WebBanDienThoai.service.CategoryService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

	
	private final CategoryRepository categoryRepo;
	@Override
	public String save(CategoryDTO categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setImage(categoryRequest.getImage());
		Category savedCategory = categoryRepo.save(category);
		
		return "saved Category successfully";
	}
	@Override
	public List<CategoryDTO> listAll() {
		List<Category> listCategory = categoryRepo.findAll();
		List<CategoryDTO> listCategoryResponse = listCategory.stream().map((category) -> {
			CategoryDTO categoryResponse = new CategoryDTO();
			categoryResponse.setId(category.getId());
			categoryResponse.setImage(category.getImage());
			categoryResponse.setName(category.getName());
			return categoryResponse;
		}).toList();
		return listCategoryResponse;
	}
	
}
