package ar.edu.unju.edm.modelo;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;


public class ProductoParaVender extends Producto{
	
	private Float cantidad;
	public ProductoParaVender() {
		
	}
	public ProductoParaVender(String nombre, String codigo, Float precio, Float disponibilidad, Long id, Float cantidad) {
        super(id, nombre, codigo, precio, disponibilidad);
        this.cantidad = cantidad;
    }
	public ProductoParaVender(String nombre, String codigo, Float precio, Float disponibilidad, Float cantidad) {
        super(nombre, codigo, precio, disponibilidad);
        this.cantidad = cantidad;
    }
	
	public void aumentarCantidad() {
        this.cantidad++;
    }
	public Float getCantidad() {
        return cantidad;
    }
	public Float getTotal() {
        return this.getPrecio() *this.cantidad;
    }
	
}
