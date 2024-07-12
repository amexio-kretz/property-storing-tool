package repository;

import entity.relation.property.PropertyBought;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyBoughtRepository extends BaseRepository<PropertyBought> {
  public List<PropertyBought> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyBought> propertyBoughts;
        em.getTransaction().begin();

        try {
            propertyBoughts = em.createQuery(
                            "SELECT pb FROM PropertyBought pb " +
                                    "JOIN pb.property p " +
                                    "WHERE p.id = :propertyId", PropertyBought.class)
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

        return propertyBoughts;
    }
}
