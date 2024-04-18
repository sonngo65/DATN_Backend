package com.DATN.WebBanDienThoai.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DATN.WebBanDienThoai.payload.CategoryDTO;
import com.DATN.WebBanDienThoai.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {
	private final CategoryService categoryService;
	
	
	@PostMapping
	public ResponseEntity<String> addCategory(@RequestBody CategoryDTO categoryRequest ){
		String response = categoryService.save(categoryRequest);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		List<CategoryDTO> categoriesResponse = categoryService.listAll();
		return new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
	}
}
