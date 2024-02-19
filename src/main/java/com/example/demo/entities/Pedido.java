package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="fecha_pedido")
	private Timestamp fechaPedido;

	//bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(
		name="pedido_producto"
		, joinColumns={
			@JoinColumn(name="id_pedido")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_producto")
			}
		)
	private List<Producto> productos;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;

	//bi-directional many-to-one association to PedidoProducto
	@OneToMany(mappedBy="pedido")
	private List<PedidoProducto> pedidoProductos;


	public Pedido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PedidoProducto> getPedidoProductos() {
		return this.pedidoProductos;
	}

	public void setPedidoProductos(List<PedidoProducto> pedidoProductos) {
		this.pedidoProductos = pedidoProductos;
	}

	public PedidoProducto addPedidoProductos(PedidoProducto pedidoProductos) {
		getPedidoProductos().add(pedidoProductos);
		pedidoProductos.setPedido(this);

		return pedidoProductos;
	}

	public PedidoProducto removePedidoProductos(PedidoProducto pedidoProductos) {
		getPedidoProductos().remove(pedidoProductos);
		pedidoProductos.setPedido(null);

		return pedidoProductos;
	}

}