package ar.edu.unju.edm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.modelo.Producto;
import ar.edu.unju.edm.modelo.Usuario;

@Service
public interface IProductoService {
	public void guardarProd(Producto producto);
	public List<Producto> listarprod();
	public Producto FindById(Long id);
	public void eliminarProducto(Long id);
	public Producto modProducto(Producto producto);
	
}
