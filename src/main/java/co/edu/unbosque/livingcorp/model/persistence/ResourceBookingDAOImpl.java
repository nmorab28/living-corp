package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.ResourceBooking;
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
public class ResourceBookingDAOImpl implements PersistenceDAO<ResourceBooking, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public ResourceBooking save(ResourceBooking entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("La reserva de recurso ya está registrada");
        }
    }

    @Override
    public ResourceBooking find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(ResourceBooking.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Reserva de recurso no encontrada");
        }
    }

    @Override
    public List<ResourceBooking> findAll() {
        return em.createQuery("SELECT resBooking FROM ResourceBooking resBooking", ResourceBooking.class).getResultList();
    }

    @Override
    public boolean update(ResourceBooking entity) throws ObjectNotFoundException {
        try {
            em.merge(entity);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Reserva de recurso no actualizada");
        }
    }

    @Override
    public boolean delete(ResourceBooking entity) throws ObjectNotFoundException {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Reserva de recurso no eliminada");
        }
    }
}