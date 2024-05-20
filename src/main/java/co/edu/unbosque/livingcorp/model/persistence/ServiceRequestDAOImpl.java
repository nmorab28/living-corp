package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.ServiceRequest;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ServiceRequestDAOImpl implements PersistenceDAO<ServiceRequest, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public ServiceRequest save(ServiceRequest entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
            throw new RepeatedObjectException("Error al guardar el ServiceRequest: " + e.getMessage());
        }
    }

    @Override
    public ServiceRequest find(Integer idEntity) throws ObjectNotFoundException {
        ServiceRequest serviceRequest = em.find(ServiceRequest.class, idEntity);
        if (serviceRequest == null) {
            throw new ObjectNotFoundException("ServiceRequest no encontrado con ID: " + idEntity);
        }
        return serviceRequest;
    }

    @Override
    public List<ServiceRequest> findAll() {
        return em.createQuery("SELECT sr FROM ServiceRequest sr", ServiceRequest.class).getResultList();
    }

    @Override
    public boolean update(ServiceRequest entity) throws ObjectNotFoundException {
        try {
            em.merge(entity);
            return true;
        } catch (Exception e) {
            throw new ObjectNotFoundException("Error al actualizar el ServiceRequest: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(ServiceRequest entity) throws ObjectNotFoundException {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        } catch (Exception e) {
            throw new ObjectNotFoundException("Error al eliminar el ServiceRequest: " + e.getMessage());
        }
    }
}
