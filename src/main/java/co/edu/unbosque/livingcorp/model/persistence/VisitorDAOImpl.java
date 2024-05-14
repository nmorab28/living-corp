package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.Visitor;
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
public class VisitorDAOImpl implements PersistenceDAO<Visitor, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public Visitor save(Visitor entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("El visitante ya está registrado");
        }
    }

    @Override
    public Visitor find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(Visitor.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Visitante no encontrado");
        }
    }

    @Override
    public List<Visitor> findAll() {
        return em.createQuery("SELECT v FROM Visitor v", Visitor.class).getResultList();
    }

    @Override
    public boolean update(Visitor entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.merge(entity);
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Visitante no actualizado");
        }
        return aux;
    }

    @Override
    public boolean delete(Visitor entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Visitante no eliminado");
        }
        return aux;
    }
}
