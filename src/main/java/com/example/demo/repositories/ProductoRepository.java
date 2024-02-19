package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Producto;

import jakarta.transaction.Transactional;

public interface ProductoRepository extends JpaRepository<Producto,Serializable>{
	
	@Bean
	public abstract List<Producto> findAll();
	public abstract Producto findById(int id);
	
	@Transactional
	public abstract Producto save(Producto p);
	
	@Transactional
	public abstract void deleteById(int id);

}
