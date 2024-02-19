package com.example.demo.repositories;
import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.PedidoProducto;

import jakarta.transaction.Transactional;;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto,Integer>{
	
	@Bean
	public abstract List<PedidoProducto> findAll();
	@Query("SELECT pp FROM PedidoProducto pp WHERE pp.pedido.user.id = :userId")
	List<PedidoProducto> findByIdUser(@Param("userId") int userId);
	
	@Transactional
	public abstract PedidoProducto save(PedidoProducto pp);

}
