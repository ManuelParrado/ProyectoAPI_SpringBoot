package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtSecurity.AutenticadorJWT;
import com.example.demo.controladores.DTO;
import com.example.demo.entidades.Usuaria;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRep;
	
	@GetMapping("getAll")
	public List<DTO> getAll() {
		
		 List<DTO> listaUsuariosDTO= new ArrayList<DTO>();
		 List<User> listaUsuarios=userRep.findAll();
		 
		 for(User u: listaUsuarios) {
			 DTO dtoUsuario=new DTO();
			 dtoUsuario.put("id", u.getId());
			 dtoUsuario.put("nombre", u.getNombre());
			 dtoUsuario.put("apellidos", u.getApellidos());
			 dtoUsuario.put("email", u.getEmail());
			 dtoUsuario.put("password", u.getPassword());
			 dtoUsuario.put("imagen", u.getImagen());
			 dtoUsuario.put("rol", u.getRol());
			 
			 listaUsuariosDTO.add(dtoUsuario);	 
		 }
		 return	listaUsuariosDTO;
		
	}
	
	@PostMapping(path="/getToken", consumes= MediaType.APPLICATION_JSON_VALUE)
	public DTO autenticaUsuario(@RequestBody User datos,
			 HttpServletResponse response) {
		DTO dto=new DTO();
		User uAutenticado = userRep.findByEmailAndPassword(datos.getEmail(),datos.getPassword());
		if(uAutenticado!=null) {	
			dto.put("jwt", AutenticadorJWT.codificaJWT(uAutenticado));
			return dto;
		}
		dto.put("jwt", "fallo");
		
		return dto;
	}
	
	@GetMapping("/getUser/{token}")
	public DTO getAutenticado(@PathVariable(value = "token") String token) {
		
		DTO dtoUsuario=new DTO();
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJWT(token);

		User u=userRep.findById(idUsuarioAutenticado);
		if(u!=null) {
			
			dtoUsuario.put("resultado","ok");
			dtoUsuario.put("nombre", u.getNombre());
			dtoUsuario.put("apellidos", u.getApellidos());
			dtoUsuario.put("email", u.getEmail());
			dtoUsuario.put("rol", u.getRol());
					
		}
		else dtoUsuario.put("resultado","null");
		
		return dtoUsuario;
	}
	
	@PostMapping(path="/insertUser", consumes= MediaType.APPLICATION_JSON_VALUE)
	public DTO insertUser(@RequestBody User usuario) {
		
		DTO salida = new DTO();
		
		User user = userRep.findByEmail(usuario.getEmail());
		
		if (user != null) {
			salida.put("resultado", "existe");
			return salida;
		}
		
		try {
			userRep.save(usuario);
		} catch (Exception e) {
			salida.put("resultado", "error");
		}
		
		salida.put("resultado", "ok");
		
		return salida;
		
	}

}
