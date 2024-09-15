package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.CropMapping;

public interface CropMappingRepository extends JpaRepository<CropMapping, Integer>{
	Optional<CropMapping> findByCropId(int cropId);
}
