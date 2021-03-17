package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.modelo.Compra;
@Repository
public interface ICompraDAO extends CrudRepository<Compra, Long> {

}
