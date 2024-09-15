package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Crop;
import com.cg.entity.CropMapping;
import com.cg.exception.CropNotFoundException;
//import com.cg.exception.CropNotFoundException;
import com.cg.service.CropService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping("crop")
public class CropController {
	@Autowired
	private CropService cropService;
	
	
	@PostMapping("/addcrop")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public Crop addCrop(@RequestBody @Valid Crop crop) throws CropNotFoundException {
		return cropService.addCrop(crop);
		
	}
	
	@DeleteMapping("/deleteCropById/{cropId}")
	@PreAuthorize("hasAnyAuthority('farmer','admin')")
	public ResponseEntity<String> deleteCropById(@PathVariable int cropId) throws CropNotFoundException{
		String deleteCropById = cropService.deleteCropById(cropId);
		ResponseEntity<String>delete=new ResponseEntity<String>(deleteCropById,HttpStatus.ACCEPTED);
		return delete;
	}
	
	
	@PutMapping("/addcropmapping/{id}/{cropId}/{quantity}/{price}/{location}")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<CropMapping> addCropMapping(@PathVariable int id ,@PathVariable int cropId,
			@PathVariable int quantity, @PathVariable double price, 
			@PathVariable String location, Principal p) throws CropNotFoundException{
		String name = p.getName();
		CropMapping cropsMapping = cropService.addCropsMapping(id, cropId, quantity, price, location, name);
		ResponseEntity<CropMapping> responseEntity = new ResponseEntity<>(cropsMapping, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/updatecropmapping/{id}/{quantity}/{price}")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<CropMapping> updateCropMapping(@PathVariable int id, @PathVariable int quantity, 
			@PathVariable double price) throws CropNotFoundException{
		CropMapping cropsMapping = cropService.updateCrops(id, quantity, price);
		ResponseEntity<CropMapping> responseEntity = new ResponseEntity<>(cropsMapping, HttpStatus.OK);
		return responseEntity;
	}
	
	
	@GetMapping("/displaycropbyid/{id}")
	@PreAuthorize("hasAnyAuthority('dealer')")
	public ResponseEntity<CropMapping> displayCropById(@PathVariable int id) throws CropNotFoundException {
		CropMapping displayCropById = cropService.displayCropById(id);
		ResponseEntity<CropMapping> responseEntity = new ResponseEntity<>(displayCropById, HttpStatus.OK);
		return responseEntity;
	}
	
	
	@GetMapping("/displayallcropmaps")
	@PreAuthorize("hasAnyAuthority('farmer','dealer','admin')")
	public ResponseEntity<List<CropMapping>> displayAllCrops() {
		List<CropMapping> allcrops = cropService.displayAllCrops();
		ResponseEntity<List<CropMapping>> responseEntity = new ResponseEntity<>(allcrops, HttpStatus.OK);
		return responseEntity;
	}


	@PutMapping("/deletecropmapping/{id}")
	@PreAuthorize("hasAnyAuthority('farmer','admin')")
	public ResponseEntity<String> deleteCropMappingId(@PathVariable int id) throws CropNotFoundException {
		String deleteCropMappingId = cropService.deleteCropMappingId(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(deleteCropMappingId, HttpStatus.OK);
		return responseEntity;
	}
	
	
}
