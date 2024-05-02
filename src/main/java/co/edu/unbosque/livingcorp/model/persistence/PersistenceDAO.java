package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;

import java.util.List;

public interface PersistenceDAO<T, K>{

    T save(T entity) throws RepeatedObjectException;

    T find(K idEntity) throws ObjectNotFoundException;

    List<T> findAll();

    boolean update(T entity) throws ObjectNotFoundException;

    boolean delete(T etity) throws ObjectNotFoundException;

}
