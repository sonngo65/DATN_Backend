package com.DATN.WebBanDienThoai.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	boolean upload(MultipartFile file);
}
