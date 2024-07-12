package repository;

import entity.relation.property.PropertyPartnerAgency;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyPartnerAgencyRepository extends BaseRepository<PropertyPartnerAgency> {
    public List<PropertyPartnerAgency> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyPartnerAgency> propertyPartnerAgencies;
        em.getTransaction().begin();

        try {
            propertyPartnerAgencies = em.createQuery(
                            "SELECT ppa FROM PropertyPartnerAgency ppa " +
                                    "JOIN ppa.property p " +
                                    "WHERE p.id = :propertyId", PropertyPartnerAgency.class)
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

        return propertyPartnerAgencies;
    }
}
