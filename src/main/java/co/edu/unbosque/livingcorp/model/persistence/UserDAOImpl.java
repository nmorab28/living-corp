package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.User;
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
public class UserDAOImpl implements PersistenceDAO<User, String> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public User save(User entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("El usuario ya está registrado");
        }
    }

    @Override
    public User find(String idEntity) throws ObjectNotFoundException {
        try {
            return em.find(User.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public boolean update(User entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.merge(entity);
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Usuario no actualizado");
        }
        return aux;
    }

    @Override
    public boolean delete(User entity) throws ObjectNotFoundException {
        boolean aux = false;
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            aux = true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Usuario no eliminado");
        }
        return aux;
    }
}
