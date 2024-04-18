package com.DATN.WebBanDienThoai.service;

import java.util.List;

import com.DATN.WebBanDienThoai.payload.CategoryDTO;

public interface CategoryService {
 	String save(CategoryDTO categoryRequest);

	List<CategoryDTO> listAll();
}
