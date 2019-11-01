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

import br.com.catapan.data.vo.v1.ClientVO;
import br.com.catapan.services.ClientServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Client Endpoint") 
@RestController
@RequestMapping("/api/client/v1")
public class ClientController {
	
	@Autowired
	private ClientServices service;
	
	@ApiOperation(value = "Find all clients" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<ClientVO> findAll() {
		List<ClientVO> clients =  service.findAll();
		clients
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(ClientController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return clients;
	}	
	
	@ApiOperation(value = "Find a specific client by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO findById(@PathVariable("id") Long id) {
		ClientVO clientVO = service.findById(id);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(id)).withSelfRel());
		return clientVO;
	}	
	
	@ApiOperation(value = "Create a new client")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO create(@RequestBody ClientVO client) {
		ClientVO clientVO = service.create(client);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(clientVO.getKey())).withSelfRel());
		return clientVO;
	}
	
	@ApiOperation(value = "Update a specific client")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO update(@RequestBody ClientVO client) {
		ClientVO clientVO = service.update(client);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(clientVO.getKey())).withSelfRel());
		return clientVO;
	}	
	
	@ApiOperation(value = "Delete a specific client by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}