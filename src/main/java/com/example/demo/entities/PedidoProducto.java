package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the pedido_producto database table.
 * 
 */
@Entity
@Table(name="pedido_producto")
@NamedQuery(name="PedidoProducto.findAll", query="SELECT p FROM PedidoProducto p")
public class PedidoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;



	public PedidoProducto() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}