package repository;

import entity.relation.property.PropertyNotarySeller;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyNotarySellerRepository extends BaseRepository<PropertyNotarySeller> {
 public List<PropertyNotarySeller> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyNotarySeller> propertyNotarySellers;
        em.getTransaction().begin();

        try {
            propertyNotarySellers = em.createQuery(
                            "SELECT pns FROM PropertyNotarySeller pns " +
                                    "JOIN pns.property p " +
                                    "WHERE p.id = :propertyId", PropertyNotarySeller.class)
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

        return propertyNotarySellers;
    }
}
