package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String apellidos;

	private String email;

	private String imagen;

	private String nombre;

	private String password;

	private String rol;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="user")
	private List<Pedido> pedidos;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setUser(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setUser(null);

		return pedido;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", apellidos=" + apellidos + ", email=" + email + ", imagen=" + imagen + ", nombre="
				+ nombre + ", password=" + password + ", rol=" + rol + ", pedidos=" + pedidos + "]";
	}

}