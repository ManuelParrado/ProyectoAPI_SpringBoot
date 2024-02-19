package com.example.demo.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoProducto;
import com.example.demo.entities.Producto;
import com.example.demo.entities.User;
import com.example.demo.repositories.PedidoProductoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pedidoproducto")
public class PedidoProductoController {
	
	@Autowired
	PedidoProductoRepository ppr;
	
	@Autowired
	PedidoRepository pr;
	
	@Autowired
	ProductoRepository pror;
	
	@Autowired
	UserRepository ur;
	
	@GetMapping("/getAll")
	public List<DTO> getAllPedidoProducto(){
		List<DTO> listaSalida= new  ArrayList<DTO>();
		List<PedidoProducto> listaPP = ppr.findAll();
		
		for(PedidoProducto pp: listaPP) {
			DTO dtoPedidoProducto=new DTO();
			dtoPedidoProducto.put("id", pp.getId());
			dtoPedidoProducto.put("nombre_usuario", pp.getPedido().getUser().getNombre());
			dtoPedidoProducto.put("fecha_pedido", pp.getPedido().getFechaPedido());
			dtoPedidoProducto.put("producto", pp.getProducto().getNombreProducto());
			listaSalida.add(dtoPedidoProducto);
		}
		
		return listaSalida;
	}
	
	@GetMapping("/getPedidosByToken/{token}")
	public List<DTO> getAllPedidoProductoByUsuario(@PathVariable(value = "token") String token){
		
		List<DTO> listaSalida= new  ArrayList<DTO>();
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJWT(token);
		List<PedidoProducto> listaPP = ppr.findByIdUser(idUsuarioAutenticado);
		
		for(PedidoProducto pp: listaPP) {
			DTO dtoPedidoProducto=new DTO();
			
			dtoPedidoProducto.put("id", pp.getId());
			dtoPedidoProducto.put("id_pedido", pp.getPedido().getId());
			dtoPedidoProducto.put("fecha_pedido", pp.getPedido().getFechaPedido());
			
			dtoPedidoProducto.put("id_producto", pp.getProducto().getId());
			dtoPedidoProducto.put("nombre_producto", pp.getProducto().getNombreProducto());
			dtoPedidoProducto.put("categoria", pp.getProducto().getCategoria());
			dtoPedidoProducto.put("precio", pp.getProducto().getPrecio());
			dtoPedidoProducto.put("imagen", pp.getProducto().getImagen());
			dtoPedidoProducto.put("descripcion", pp.getProducto().getDescripcion());
			listaSalida.add(dtoPedidoProducto);
		}
		
		return listaSalida;
	}
	
	
	@Transactional
	@PostMapping(path="/insertarPedido/{tokenU}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public DTO insertPedido(@PathVariable String tokenU, @RequestBody List<Producto> productos) {
		
		DTO salida = new DTO();
		
		List<Producto> productosInsert = new ArrayList<Producto>();
		
		int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJWT(tokenU);
		User usu = ur.findById(idUsuarioAutenticado);
		System.out.println(usu.getNombre());
		Pedido pedido = new Pedido();
		
		for (Producto p : productos) {
			productosInsert.add(pror.findById(p.getId()));
		}
		
		try {
			// Si la insercion se hace correctamente devuelve ok
			pedido.setUser(usu);
			pedido.setProductos(productosInsert);
			pr.save(pedido);
			
			salida.put("resultado", "ok");
			
		} catch (Exception e) {
			// Sino, devuelve error
			salida.put("resultado", "error");
			return salida;
		}
		
		return salida;
		
	}
	
	

}
