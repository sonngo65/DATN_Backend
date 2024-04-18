package com.DATN.WebBanDienThoai.payload;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDetailRequest {
	private String option1;
	private String option2;
	private String image;
	private int status;
	private double price;
	private double orginalPrice;
	private int quantity;
}
