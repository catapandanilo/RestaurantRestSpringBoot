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

import br.com.catapan.data.vo.v1.OrderVO;
import br.com.catapan.services.OrderServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Order Endpoint") 
@RestController
@RequestMapping("/api/order/v1")
public class OrderController {
	
	@Autowired
	private OrderServices service;
	
	@ApiOperation(value = "Find all orders" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<OrderVO> findAll() {
		List<OrderVO> orders =  service.findAll();
		orders
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(OrderController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return orders;
	}	
	
	@ApiOperation(value = "Find a specific order by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public OrderVO findById(@PathVariable("id") Long id) {
		OrderVO orderVO = service.findById(id);
		orderVO.add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
		return orderVO;
	}	
	
	@ApiOperation(value = "Create a new order")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public OrderVO create(@RequestBody OrderVO order) {
		OrderVO orderVO = service.create(order);
		orderVO.add(linkTo(methodOn(OrderController.class).findById(orderVO.getKey())).withSelfRel());
		return orderVO;
	}
	
	@ApiOperation(value = "Update a specific order")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public OrderVO update(@RequestBody OrderVO order) {
		OrderVO orderVO = service.update(order);
		orderVO.add(linkTo(methodOn(OrderController.class).findById(orderVO.getKey())).withSelfRel());
		return orderVO;
	}	
	
	@ApiOperation(value = "Delete a specific order by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}