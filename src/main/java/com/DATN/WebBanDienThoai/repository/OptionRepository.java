package com.DATN.WebBanDienThoai.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DATN.WebBanDienThoai.entity.Option;


@Repository
public interface OptionRepository extends JpaRepository<Option, UUID>{
	
	
	@Query(value = "select * from option where parent_id = :id ",nativeQuery = true)
	List<Option> findAllByParentId(UUID id);
}
