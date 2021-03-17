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
public class Usuario implements Serializable {
	/**
	 * 
	 */	
	private static final long serialVersionUID= 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column
	String userName;
	@Column
	String password;
	@Column
	String dni;
	@Column
	String nombre;
	@Column
	String apellido;
	@Column
	String tipo;
	public Usuario() {
		
	}
	public Usuario(Long id, String userName, String password, String dni, String nombre, String apellido,
			String tipo) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreusuario() {
		return userName;
	}
	public void setNombreusuario(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreusuario=" + userName + ", password=" + password + ", dni=" + dni
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", tipo=" + tipo + "]";
	}
	
	
	
	
}
