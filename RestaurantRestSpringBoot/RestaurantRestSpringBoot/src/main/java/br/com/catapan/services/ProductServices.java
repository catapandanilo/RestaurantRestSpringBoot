package br.com.catapan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catapan.adapter.DozerConverter;
import br.com.catapan.data.model.Product;
import br.com.catapan.data.vo.v1.ProductVO;
import br.com.catapan.exception.ResourceNotFoundException;
import br.com.catapan.repository.ProductRepository;

@Service
public class ProductServices {
	
	@Autowired
	ProductRepository repository;
		
	public ProductVO create(ProductVO product) {
		Product entity = DozerConverter.parseObject(product, Product.class);
		ProductVO vo = DozerConverter.parseObject(repository.save(entity), ProductVO.class);
		return vo;
	}
	
	public List<ProductVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), ProductVO.class);
	}	
	
	public ProductVO findById(Long id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, ProductVO.class);
	}
		
	public ProductVO update(ProductVO product) {
		Product entity = repository.findById(product.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		
		ProductVO vo = DozerConverter.parseObject(repository.save(entity), ProductVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}