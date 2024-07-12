package repository;

import entity.relation.property.PropertyThirdParty;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyThirdPartyRepository extends BaseRepository<PropertyThirdParty> {
    public List<PropertyThirdParty> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyThirdParty> propertyThirdParties;
        em.getTransaction().begin();

        try {
            propertyThirdParties = em.createQuery(
                            "SELECT ptp FROM PropertyThirdParty ptp " +
                                    "JOIN ptp.property p " +
                                    "WHERE p.id = :propertyId", PropertyThirdParty.class)
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

        return propertyThirdParties;
    }
}
