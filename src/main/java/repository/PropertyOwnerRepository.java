package repository;

import entity.relation.property.PropertyOwner;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyOwnerRepository extends BaseRepository<PropertyOwner> {
public List<PropertyOwner> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyOwner> propertyOwners;
        em.getTransaction().begin();

        try {
            propertyOwners = em.createQuery(
                            "SELECT po FROM PropertyOwner po " +
                                    "JOIN po.property p " +
                                    "WHERE p.id = :propertyId", PropertyOwner.class)
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

        return propertyOwners;
    }
}
