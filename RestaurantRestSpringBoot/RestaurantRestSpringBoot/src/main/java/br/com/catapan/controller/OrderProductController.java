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

import br.com.catapan.data.vo.v1.OrderProductVO;
import br.com.catapan.services.OrderProductServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "OrderProduct Endpoint") 
@RestController
@RequestMapping("/api/orderProduct/v1")
public class OrderProductController {
	
	@Autowired
	private OrderProductServices service;
	
	@ApiOperation(value = "Find all order products" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<OrderProductVO> findAll() {
		List<OrderProductVO> orderProducts =  service.findAll();
		orderProducts
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(OrderProductController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return orderProducts;
	}	
	
	@ApiOperation(value = "Find a specific order product by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public OrderProductVO findById(@PathVariable("id") Long id) {
		OrderProductVO orderProductVO = service.findById(id);
		orderProductVO.add(linkTo(methodOn(OrderProductController.class).findById(id)).withSelfRel());
		return orderProductVO;
	}	
	
	@ApiOperation(value = "Create a new order product")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public OrderProductVO create(@RequestBody OrderProductVO orderProduct) {
		OrderProductVO orderProductVO = service.create(orderProduct);
		orderProductVO.add(linkTo(methodOn(OrderProductController.class).findById(orderProductVO.getKey())).withSelfRel());
		return orderProductVO;
	}
	
	@ApiOperation(value = "Update a specific order product")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public OrderProductVO update(@RequestBody OrderProductVO orderProduct) {
		OrderProductVO orderProductVO = service.update(orderProduct);
		orderProductVO.add(linkTo(methodOn(OrderProductController.class).findById(orderProductVO.getKey())).withSelfRel());
		return orderProductVO;
	}	
	
	@ApiOperation(value = "Delete a specific order product by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}