package ar.edu.unju.edm.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class ProductoVendido {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float cantidad;
    private Float precio;
    private String nombre;
    private String codigo;
    @ManyToOne
    @JoinColumn
    private Compra compra;
    public ProductoVendido() {
    
    }
	public ProductoVendido(Long id, Float cantidad, Float precio, String nombre, String codigo, Compra compra) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.nombre = nombre;
		this.codigo = codigo;
		this.compra = compra;
	}
	public ProductoVendido(Float cantidad,Float precio,String nombre, String codigo,Compra compra) {
	super();
	this.cantidad = cantidad;
	this.precio =precio;
	this.nombre = nombre;
	this.codigo = codigo;
	this.compra = compra;
	}
	public float getTotal() {
		return this.cantidad * this.precio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
    
    
}
