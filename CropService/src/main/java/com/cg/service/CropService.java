package com.cg.service;

import java.util.List;

import com.cg.entity.Crop;
//import com.cg.exception.CropNotFoundException;
import com.cg.entity.CropMapping;
import com.cg.exception.CropNotFoundException;



public interface CropService {
	
	public Crop addCrop(Crop crop) throws CropNotFoundException;
	
	public String deleteCropById(int cropId) throws CropNotFoundException;
	
	public CropMapping addCropsMapping(int id, int cropId, int quantity, 
			double price, String location, String name) throws CropNotFoundException;
	
	CropMapping updateCrops(int id, int quantity, double price) throws CropNotFoundException;
	
	public CropMapping displayCropById(int id) throws CropNotFoundException;
	
	public List<CropMapping> displayAllCrops(); 
	
	public String deleteCropMappingId(int id) throws CropNotFoundException;

}
