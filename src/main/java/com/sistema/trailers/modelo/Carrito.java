package com.sistema.trailers.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carrito {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(nullable = true)
    private String imagen;
	@Column(nullable = true)
    private String descripcion;
	@Column(nullable = true)
    private double precio;
	@Column(nullable = true)
    private int cantidad;
    
    
    
	public Carrito() {
		super();
	}



	public Carrito(int id, String imagen, String descripcion, double precio, int cantidad) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	



	
     
}
