package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TransactionRequiredException;

import java.util.List;

@Stateless
public class PropertyDAOImpl implements PersistenceDAO<Property, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public Property save(Property entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("La propiedad ya está registrada");
        }
    }

    @Override
    public Property find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(Property.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Propiedad no encontrada");
        }
    }

    @Override
    public List<Property> findAll() {
        return em.createQuery("SELECT p FROM Property p", Property.class).getResultList();
    }

    @Override
    public boolean update(Property entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.merge(entity);
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Propiedad no actualizada");
        }
        return aux;
    }

    @Override
    public boolean delete(Property entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Propiedad no eliminada");
        }
        return aux;
    }
}
