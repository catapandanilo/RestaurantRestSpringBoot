package br.com.catapan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catapan.adapter.DozerConverter;
import br.com.catapan.data.model.Order;
import br.com.catapan.data.vo.v1.OrderVO;
import br.com.catapan.exception.ResourceNotFoundException;
import br.com.catapan.repository.OrderRepository;

@Service
public class OrderServices {
	
	@Autowired
	OrderRepository repository;
		
	public OrderVO create(OrderVO order) {
		Order entity = DozerConverter.parseObject(order, Order.class);
		OrderVO vo = DozerConverter.parseObject(repository.save(entity), OrderVO.class);
		return vo;
	}
	
	public List<OrderVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), OrderVO.class);
	}	
	
	public OrderVO findById(Long id) {
		Order entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, OrderVO.class);
	}
		
	public OrderVO update(OrderVO order) {
		Order entity = repository.findById(order.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setClientId(order.getClientId());
		entity.setOrderDate(order.getOrderDate());
		entity.setProgress(order.getProgress());
		entity.setObservation(order.getObservation());
		
		OrderVO vo = DozerConverter.parseObject(repository.save(entity), OrderVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Order entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}