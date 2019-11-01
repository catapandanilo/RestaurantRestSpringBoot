package br.com.catapan.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.catapan.data.vo.v1.ProductVO;
import br.com.catapan.services.ProductServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Product Endpoint") 
@RestController
@RequestMapping("/api/product/v1")
public class ProductController {
	
	@Autowired
	private ProductServices service;
	
	@ApiOperation(value = "Find all products" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<ProductVO> findAll() {
		List<ProductVO> products =  service.findAll();
		products
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(ProductController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return products;
	}	
	
	@ApiOperation(value = "Find a specific product by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ProductVO findById(@PathVariable("id") Long id) {
		ProductVO productVO = service.findById(id);
		productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		return productVO;
	}	
	
	@ApiOperation(value = "Create a new product")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ProductVO create(@RequestBody ProductVO product) {
		ProductVO productVO = service.create(product);
		productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getKey())).withSelfRel());
		return productVO;
	}
	
	@ApiOperation(value = "Update a specific product")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ProductVO update(@RequestBody ProductVO product) {
		ProductVO productVO = service.update(product);
		productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getKey())).withSelfRel());
		return productVO;
	}	
	
	@ApiOperation(value = "Delete a specific product by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}