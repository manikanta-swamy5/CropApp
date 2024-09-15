package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.OrderDTO;

public interface OrderDtoRepo extends JpaRepository<OrderDTO, Integer>{

}
