package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TransactionRequiredException;

import java.util.List;

@Stateless
public class ResourceDAOImpl implements PersistenceDAO<Resource, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public Resource save(Resource entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("El recurso ya está registrado");
        }
    }

    @Override
    public Resource find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(Resource.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Recurso no encontrado");
        }
    }

    @Override
    public List<Resource> findAll() {
        return em.createQuery("SELECT res FROM Resource res", Resource.class).getResultList();
    }

    @Override
    public boolean update(Resource entity) throws ObjectNotFoundException {
        try {
            em.merge(entity);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Recurso no actualizado");
        }
    }

    @Override
    public boolean delete(Resource entity) throws ObjectNotFoundException {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Recurso no eliminado");
        }
    }
}