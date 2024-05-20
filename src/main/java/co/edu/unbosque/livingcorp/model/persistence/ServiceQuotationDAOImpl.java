package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.ServiceQuotation;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ServiceQuotationDAOImpl implements PersistenceDAO<ServiceQuotation, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public ServiceQuotation save(ServiceQuotation entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
            throw new RepeatedObjectException("Error al guardar el ServiceQuotation: " + e.getMessage());
        }
    }

    @Override
    public ServiceQuotation find(Integer idEntity) throws ObjectNotFoundException {
        ServiceQuotation serviceQuotation = em.find(ServiceQuotation.class, idEntity);
        if (serviceQuotation == null) {
            throw new ObjectNotFoundException("ServiceQuotation no encontrado con ID: " + idEntity);
        }
        return serviceQuotation;
    }

    @Override
    public List<ServiceQuotation> findAll() {
        return em.createQuery("SELECT sq FROM ServiceQuotation sq", ServiceQuotation.class).getResultList();
    }

    @Override
    public boolean update(ServiceQuotation entity) throws ObjectNotFoundException {
        try {
            em.merge(entity);
            return true;
        } catch (Exception e) {
            throw new ObjectNotFoundException("Error al actualizar el ServiceQuotation: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(ServiceQuotation entity) throws ObjectNotFoundException {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        } catch (Exception e) {
            throw new ObjectNotFoundException("Error al eliminar el ServiceQuotation: " + e.getMessage());
        }
    }
}
