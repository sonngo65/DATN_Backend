package com.DATN.WebBanDienThoai.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.DATN.WebBanDienThoai.payload.ProductDTO;

public interface ProductService {
	boolean save(ProductDTO productRequest);

	ProductDTO getById(UUID id);
}
