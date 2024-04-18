package com.DATN.WebBanDienThoai.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.DATN.WebBanDienThoai.entity.Option;
import com.DATN.WebBanDienThoai.entity.OptionDetail;
import com.DATN.WebBanDienThoai.entity.Product;
import com.DATN.WebBanDienThoai.payload.ChildOptionDTO;
import com.DATN.WebBanDienThoai.payload.OptionDTO;
import com.DATN.WebBanDienThoai.payload.OptionDetailRequest;
import com.DATN.WebBanDienThoai.payload.ProductDTO;
import com.DATN.WebBanDienThoai.repository.OptionDetailRepository;
import com.DATN.WebBanDienThoai.repository.OptionRepository;
import com.DATN.WebBanDienThoai.repository.ProductRepository;
import com.DATN.WebBanDienThoai.service.ProductService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepo;
	private final OptionRepository optionRepo;
	private final OptionDetailRepository optionDetailRepo;
	
	@Override
	public boolean save(ProductDTO productRequest) {
		Product product = new Product();
		
		product.setName(productRequest.getName());
		product.setContent(productRequest.getContent());
		product.setSummary(productRequest.getSummary());
		product.setPrice(productRequest.getPrice());
		product.setOriginalPrice(productRequest.getOriginalPrice());
		Product productSaved = productRepo.save(product);
		Map<String,Option> childOptionSavedList =  new HashMap<String,Option>();
		
		
		productRequest.getOptions().stream().forEach((optionRequest)-> {
			Option option = new Option();
			option.setTitle(optionRequest.getTitle());
			option.setProduct(productSaved);
			Option optionSaved = optionRepo.save(option);
			optionRequest.getChildOptions().stream().forEach((childOptionRequest) -> {
				Option childOption = new Option();
				childOption.setName(childOptionRequest.getName());
				childOption.setOption(optionSaved);
				Option childOptionSaved = optionRepo.save(childOption);
				childOptionSavedList.put(childOptionSaved.getName(), childOptionSaved);
			});
		
		});
		
		
		productRequest.getOptionDetails().stream().forEach((optionDetailRequest)->{
			OptionDetail optionDetail = new OptionDetail();	
			Option option1 = childOptionSavedList.get(optionDetailRequest.getOption1());
				Option option2 = childOptionSavedList.get(optionDetailRequest.getOption2());
				optionDetail.setProduct(productSaved);
				optionDetail.setOption1(option1);
				optionDetail.setOption2(option2);
				optionDetail.setPrice(optionDetailRequest.getPrice());
				optionDetail.setImage(optionDetailRequest.getImage());
				optionDetail.setQuantity(optionDetailRequest.getQuantity());
				optionDetailRepo.save(optionDetail);
		});
		
		
		return true;
	}

	@Override
	public ProductDTO getById(UUID id) {
		Product product = productRepo.findById(id).get();
		ProductDTO productResponse = new ProductDTO();
		productResponse.setName(product.getName());
		productResponse.setContent(product.getContent());
		productResponse.setSummary(product.getSummary());
		productResponse.setPrice(product.getPrice());
		productResponse.setOriginalPrice(product.getOriginalPrice());
		productResponse.setOptions(product.getOptions().stream().map((option)-> {
			List<Option> childOptionList = optionRepo.findAllByParentId(option.getId());
			OptionDTO optionResponse = new OptionDTO();
			
			optionResponse.setTitle(option.getTitle());
			
			optionResponse.setChildOptions(childOptionList.stream().map((childOption)->{
				ChildOptionDTO childOptionResponse = new ChildOptionDTO();
				childOptionResponse.setName(childOption.getName());
				return childOptionResponse;
			}).toList());
			return optionResponse;
		}).toList());
		productResponse.setOptionDetails(product.getOptionDetails().stream().map((optionDetail)->{
			OptionDetailRequest optionDetailResponse = new OptionDetailRequest();
			optionDetailResponse.setOption1(optionDetail.getOption1().getName());
			optionDetailResponse.setOption2(optionDetail.getOption2().getName());
			optionDetailResponse.setPrice(optionDetail.getPrice());
			optionDetailResponse.setQuantity(optionDetail.getQuantity());
			return optionDetailResponse;
		}).toList());

		return productResponse;
	}

	
}
