package ar.edu.unju.edm.modelo;

import java.io.Serializable;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

import org.springframework.stereotype.Component;





@Component
@Entity
public class Compra implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fechaYHora;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;
    
    public Compra() {
        this.fechaYHora = fecha.obtenerFechaYHoraActual();
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getTotal() {
        Float total = 0f;
        for (ProductoVendido productoVendido : this.productos) {
            total += productoVendido.getTotal();
        }
        return total;
    }
	public Set<ProductoVendido> getProductos() {
		return productos;
	}
	public void setProductos(Set<ProductoVendido> productos) {
		this.productos = productos;
	}
	public String getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
    
}
