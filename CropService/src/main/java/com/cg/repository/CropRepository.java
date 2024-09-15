package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Integer> {
	Optional<Crop> findByCropName(String name);
}
