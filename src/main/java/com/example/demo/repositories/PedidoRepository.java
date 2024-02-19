package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pedido;

import jakarta.transaction.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Transactional
	public abstract Pedido save(Pedido p);

}
