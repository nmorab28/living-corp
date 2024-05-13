package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.Resident;
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
public class ResidentDAOImpl implements PersistenceDAO<Resident, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public Resident save(Resident entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("El residente ya está registrado");
        }
    }

    @Override
    public Resident find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(Resident.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Residente no encontrado");
        }
    }

    @Override
    public List<Resident> findAll() {
        return em.createQuery("SELECT r FROM Resident r", Resident.class).getResultList();
    }

    @Override
    public boolean update(Resident entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.merge(entity);
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Residente no actualizado");
        }
        return aux;
    }

    @Override
    public boolean delete(Resident entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Residente no eliminado");
        }
        return aux;
    }
}