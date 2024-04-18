package com.DATN.WebBanDienThoai.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DATN.WebBanDienThoai.entity.Option;
import com.DATN.WebBanDienThoai.entity.OptionDetail;

@Repository
public interface OptionDetailRepository extends JpaRepository<OptionDetail, UUID>{
	
}
