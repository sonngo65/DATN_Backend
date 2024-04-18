package com.DATN.WebBanDienThoai.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DATN.WebBanDienThoai.payload.ProductDTO;
import com.DATN.WebBanDienThoai.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@CrossOrigin
public class ProductController {
	private ProductService productServ;
	
	@PostMapping()
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productRequest){ 
		boolean isSuccess = productServ.save(productRequest);
		
		if(isSuccess)
		return new ResponseEntity<>("add product successfully",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("add product False",HttpStatus.BAD_REQUEST);

	}	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") UUID id){
		ProductDTO productResponse = productServ.getById(id);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
}
