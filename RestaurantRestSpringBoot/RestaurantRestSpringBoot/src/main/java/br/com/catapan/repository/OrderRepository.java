package br.com.catapan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.catapan.data.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
