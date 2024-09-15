package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Crop;
import com.cg.entity.CropMapping;
import com.cg.exception.CropNotFoundException;
import com.cg.repository.CropMappingRepository;
//import com.cg.exception.CropNotFoundException;
import com.cg.repository.CropRepository;

@Service
public class CropServiceImpl implements CropService {
	@Autowired
	private CropRepository cropRepo;
	
	@Autowired
	CropMappingRepository cropMappingRepository;
	

	@Override
	public Crop addCrop(Crop crop) throws CropNotFoundException {
		Optional<Crop> cropName = cropRepo.findByCropName(crop.getCropName());
		if(cropName.isEmpty()) {
			return cropRepo.save(crop); 
		}
		else {
			throw new CropNotFoundException("Crop ID not found");
			
		}
		
	}

	@Override
	public String deleteCropById(int cropId) throws CropNotFoundException {
		if(cropRepo.existsById(cropId)) {
			cropRepo.deleteById(cropId);
			return "Deleted Succesfully";
		}else {
			throw new CropNotFoundException("Crop ID not found");
		}
	
	}
	
	
	public CropMapping addCropsMapping(int id, int cropId, int quantity, double price,
			String location, String name) throws CropNotFoundException {
		
		Optional<Crop> byId = cropRepo.findById(cropId);
		
		if(byId.isPresent()) {
			CropMapping cropmap = new CropMapping();
			cropmap.setId(id);
			cropmap.setCropId(cropId);
			cropmap.setQuantity(quantity);
			cropmap.setPrice(price);
			cropmap.setLocation(location);
			cropmap.setFarmerName(name);
			
			CropMapping crop = cropMappingRepository.save(cropmap);
			return crop;
		}
		else {
			throw new CropNotFoundException("Crop ID not found");
		}
		
	}
	
	public CropMapping updateCrops(int id, int quantity, double price) throws CropNotFoundException {
		Optional<CropMapping> byId = cropMappingRepository.findById(id);
		if(byId.isPresent()) {
			CropMapping cropMapping = byId.get();
			cropMapping.setQuantity(quantity);
			cropMapping.setPrice(price);
			CropMapping save = cropMappingRepository.save(cropMapping);
			return save;
		}
		else {
			throw new CropNotFoundException("No Crop found");
		}
		
	}


	@Override
	public CropMapping displayCropById(int id) throws CropNotFoundException {
		Optional<CropMapping> byId = cropMappingRepository.findById(id);
		if(byId.isPresent()) {
			return byId.get();
		}
		else {
			throw new CropNotFoundException("Crop ID not found");
		}
		
	}


	@Override
	public List<CropMapping> displayAllCrops() {
		List<CropMapping> all = cropMappingRepository.findAll();
		return all;
	}


	@Override
	public String deleteCropMappingId(int id) throws CropNotFoundException {
		Optional<CropMapping> byId = cropMappingRepository.findById(id);
		if(byId.isPresent()) {
			cropMappingRepository.deleteById(id);
			return "Crops deleted successfully";
		}
		else {
			throw new CropNotFoundException("Crop Not Deleted");
		}
		
		
	}

}
