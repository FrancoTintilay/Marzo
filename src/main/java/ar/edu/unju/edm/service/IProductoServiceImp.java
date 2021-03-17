package ar.edu.unju.edm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.modelo.Producto;
import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.repository.IProductoDAO;
@Service
public class IProductoServiceImp implements IProductoService{
	@Autowired
	IProductoDAO iProductoDAO;
	@Override
	public void guardarProd(Producto producto) {
		// TODO Auto-generated method stub
		iProductoDAO.save(producto);
	}

	@Override
	public List<Producto> listarprod() {
		// TODO Auto-generated method stub
		return (List<Producto>) iProductoDAO.findAll();
	}

	

	@Override
	public void eliminarProducto(Long id) {
		// TODO Auto-generated method stub
		iProductoDAO.deleteById(id);
	}

	@Override
	public Producto FindById(Long id) {
		// TODO Auto-generated method stub
		return iProductoDAO.findById(id).orElse(null);
	}

	@Override
	public Producto modProducto(Producto producto) {
		// TODO Auto-generated method stub
		return iProductoDAO.save(producto);
	}

}
