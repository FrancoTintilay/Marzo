package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.modelo.Producto;
@Repository
public interface IProductoDAO extends CrudRepository<Producto, Long> {
	Producto findFirstByCodigo(String codigo);
}
