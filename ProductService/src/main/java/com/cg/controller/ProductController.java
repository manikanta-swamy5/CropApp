package com.cg.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

import com.cg.entity.Product;
import com.cg.entity.UserProfile;
import com.cg.exception.ExceptionHandle;
import com.cg.repository.UserRepository;
import com.cg.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/getprofile")
	@PreAuthorize("hasAnyAuthority('Admin')") 
	public UserProfile getprofile(Principal p, HttpServletRequest request) {
		UserProfile byUsername = repository.findByUsername(p.getName());
		return byUsername;
	}
	
	
	@GetMapping("/hello")
	@PreAuthorize("hasAnyAuthority('Admin')") 
	public String helloProduct() {
		return "hello product";
	}
	
	
	
	@PostMapping("/add")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<String> addProducts(@RequestBody Product product) {
		productService.addProducts(product);
		return new ResponseEntity<>("product added successfully", HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	@PreAuthorize("hasAnyAuthority('Admin','user')")
	public ResponseEntity<List<Product>> getAllProducts() throws ExceptionHandle {
		List<Product> Products = productService.getAllProducts();
		ResponseEntity<List<Product>> product = new ResponseEntity<List<Product>>(Products, HttpStatus.OK);
		return product;
	}

	@GetMapping("/prodid/{prodId}")
	@PreAuthorize("hasAnyAuthority('Admin','user')")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable int prodId) throws ExceptionHandle {
		Optional<Product> productById = productService.getProductById(prodId);
		ResponseEntity<Optional<Product>> product = new ResponseEntity<Optional<Product>>(productById, HttpStatus.OK);
		return product;
	}

	@GetMapping("/prodname/{prodName}")
	@PreAuthorize("hasAnyAuthority('Admin','user')")
	public ResponseEntity<Optional<Product>> getProductByName(@PathVariable String prodName) throws ExceptionHandle {
		Optional<Product> productByName = productService.getProductByName(prodName);
		ResponseEntity<Optional<Product>> product = new ResponseEntity<Optional<Product>>(productByName, HttpStatus.OK);
		return product;
	}

	@PutMapping("/update")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<String> updateProducts(@RequestBody Product product) throws ExceptionHandle {
		String updateProducts = productService.updateProducts(product);
		ResponseEntity<String> string = new ResponseEntity<String>(updateProducts, HttpStatus.OK);
		return string;
	}

	@DeleteMapping("/delete/{prodId}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<String> deleteProductById(@PathVariable int prodId) throws ExceptionHandle {
		String deleteProductById = productService.deleteProductById(prodId);
		ResponseEntity<String> string = new ResponseEntity<String>(deleteProductById, HttpStatus.OK);
		return string;
	}

	@GetMapping("/cat/{category}")
	@PreAuthorize("hasAnyAuthority('Admin','user')")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) throws ExceptionHandle {
		List<Product> productByCategory = productService.getProductByCategory(category);
		ResponseEntity<List<Product>> product = new ResponseEntity<List<Product>>(productByCategory, HttpStatus.OK);
		return product;
	}

	@GetMapping("/type/{prodType}")
	@PreAuthorize("hasAnyAuthority('Admin','user')")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable String prodType) throws ExceptionHandle {
		List<Product> productByType = productService.getProductByType(prodType);
		ResponseEntity<List<Product>> product = new ResponseEntity<List<Product>>(productByType, HttpStatus.OK);
		return product;
	}

}
