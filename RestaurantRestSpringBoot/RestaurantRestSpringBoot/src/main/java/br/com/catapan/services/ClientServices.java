package br.com.catapan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catapan.adapter.DozerConverter;
import br.com.catapan.data.model.Client;
import br.com.catapan.data.vo.v1.ClientVO;
import br.com.catapan.exception.ResourceNotFoundException;
import br.com.catapan.repository.ClientRepository;

@Service
public class ClientServices {
	
	@Autowired
	ClientRepository repository;
		
	public ClientVO create(ClientVO client) {
		Client entity = DozerConverter.parseObject(client, Client.class);
		ClientVO vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class);
		return vo;
	}
	
	public List<ClientVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), ClientVO.class);
	}	
	
	public ClientVO findById(Long id) {
		Client entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, ClientVO.class);
	}
		
	public ClientVO update(ClientVO client) {
		Client entity = repository.findById(client.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setName(client.getName());
		
		ClientVO vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Client entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}