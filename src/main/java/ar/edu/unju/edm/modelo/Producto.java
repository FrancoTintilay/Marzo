package ar.edu.unju.edm.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.stereotype.Component;
@Component
@Entity
public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID= 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column
	private String nombre;
	@Column
	private String codigo;
	@Column
	private Float precio;
	@Column
	private Float disponibilidad;
	public Producto() {
		
	}
	
	public Producto(Long id, String nombre, String codigo, Float precio, Float disponibilidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public Producto(String nombre, String codigo, Float precio, Float disponibilidad) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Float disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public void restarExistencia(Float disponibilidad) {
        this.disponibilidad -=disponibilidad;
    }

	public boolean sinExistencia() {
        return this.disponibilidad <= 0;
    }
	


}

