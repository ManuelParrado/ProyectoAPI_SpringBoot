package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controladores.DTO;
import com.example.demo.entities.Producto;
import com.example.demo.entities.User;
import com.example.demo.repositories.ProductoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoRepository proRep;
	
	@GetMapping("getAll")
	public  List<DTO> getAll() {
		
		 List<DTO> listaProductosDTO = new ArrayList<DTO>();
		 List<Producto> listaProductos = proRep.findAll();
		 
		 for(Producto p: listaProductos) {
			 DTO dtoProducto = new DTO();
			 dtoProducto.put("id", p.getId());
			 dtoProducto.put("nombre_producto", p.getNombreProducto());
			 dtoProducto.put("categoria", p.getCategoria());
			 dtoProducto.put("precio", p.getPrecio());
			 dtoProducto.put("imagen", p.getImagen());
			 dtoProducto.put("descripcion", p.getDescripcion());
			 
			 listaProductosDTO.add(dtoProducto);	 
		 }
		 return	listaProductosDTO;
		
	}
	
	@GetMapping("/getProductosById/{carritoString}")
	public List<DTO> getProductosById(@PathVariable(value = "carritoString") String carritoString){
		
		List<DTO> listaProductosDTO= new ArrayList<DTO>();
		
		String[] carritoArray = carritoString.split(",");
		
		for (String c : carritoArray) {
			int idc = Integer.parseInt(c);
			Producto p = proRep.findById(idc);
			
			DTO dtoProducto = new DTO();
			dtoProducto.put("id", p.getId());
			dtoProducto.put("nombre_producto", p.getNombreProducto());
			dtoProducto.put("categoria", p.getCategoria());
			dtoProducto.put("precio", p.getPrecio());
			dtoProducto.put("imagen", p.getImagen());
			dtoProducto.put("descripcion", p.getDescripcion());
			
			System.out.println(dtoProducto);
			
			listaProductosDTO.add(dtoProducto);
		}
		
		return listaProductosDTO;
	}
	
	

}
