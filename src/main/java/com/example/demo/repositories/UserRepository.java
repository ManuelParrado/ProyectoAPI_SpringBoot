package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Serializable>{
	
	@Bean
	public abstract List<User> findAll();
	public abstract User findById(int id);
	public abstract User findByEmailAndPassword(String email, String password);
	
	@Transactional
	public abstract User save(User u);
	
	@Transactional
	public abstract void deleteById(int id);

}
