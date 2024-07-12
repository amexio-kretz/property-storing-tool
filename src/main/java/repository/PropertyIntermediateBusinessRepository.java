package repository;

import entity.relation.property.PropertyIntermediateBusiness;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyIntermediateBusinessRepository extends BaseRepository<PropertyIntermediateBusiness> {
public List<PropertyIntermediateBusiness> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyIntermediateBusiness> propertyIntermediateBusinesses;
        em.getTransaction().begin();

        try {
            propertyIntermediateBusinesses = em.createQuery(
                            "SELECT pib FROM PropertyIntermediateBusiness pib " +
                                    "JOIN pib.property p " +
                                    "WHERE p.id = :propertyId", PropertyIntermediateBusiness.class)
                    .setParameter("propertyId",propertyId)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new StoringException(e.getCause());
        } finally {
            em.close();
        }

        return propertyIntermediateBusinesses;
    }
}
