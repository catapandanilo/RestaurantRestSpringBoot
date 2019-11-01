package br.com.catapan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catapan.adapter.DozerConverter;
import br.com.catapan.data.model.OrderProduct;
import br.com.catapan.data.vo.v1.OrderProductVO;
import br.com.catapan.exception.ResourceNotFoundException;
import br.com.catapan.repository.OrderProductRepository;

@Service
public class OrderProductServices {
	
	@Autowired
	OrderProductRepository repository;
		
	public OrderProductVO create(OrderProductVO orderProduct) {
		OrderProduct entity = DozerConverter.parseObject(orderProduct, OrderProduct.class);
		OrderProductVO vo = DozerConverter.parseObject(repository.save(entity), OrderProductVO.class);
		return vo;
	}
	
	public List<OrderProductVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), OrderProductVO.class);
	}	
	
	public OrderProductVO findById(Long id) {
		OrderProduct entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, OrderProductVO.class);
	}
		
	public OrderProductVO update(OrderProductVO orderProduct) {
		OrderProduct entity = repository.findById(orderProduct.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setOrderId(orderProduct.getOrderId());
		entity.setProductId(orderProduct.getProductId());
		entity.setQuantity(orderProduct.getQuantity());
		
		OrderProductVO vo = DozerConverter.parseObject(repository.save(entity), OrderProductVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		OrderProduct entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}