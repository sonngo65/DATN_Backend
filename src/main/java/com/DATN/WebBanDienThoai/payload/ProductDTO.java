package com.DATN.WebBanDienThoai.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String name;
	private double price;
	private double originalPrice;
	private String summary;
	private String content;
	private List<OptionDTO> options;
	private List<OptionDetailRequest> optionDetails;
}
