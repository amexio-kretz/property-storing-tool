package repository;

import entity.relation.property.PropertyNotaryBuyer;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyNotaryBuyerRepository extends BaseRepository<PropertyNotaryBuyer> {
public List<PropertyNotaryBuyer> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyNotaryBuyer> propertyNotaryBuyers;
        em.getTransaction().begin();

        try {
            propertyNotaryBuyers = em.createQuery(
                            "SELECT pnb FROM PropertyNotaryBuyer pnb " +
                                    "JOIN pnb.property p " +
                                    "WHERE p.id = :propertyId", PropertyNotaryBuyer.class)
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

        return propertyNotaryBuyers;
    }
}
