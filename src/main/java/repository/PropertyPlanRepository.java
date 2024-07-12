package repository;

import entity.relation.property.PropertyPlan;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyPlanRepository extends BaseRepository<PropertyPlan> {
    public List<PropertyPlan> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyPlan> propertyPlans;
        em.getTransaction().begin();

        try {
            propertyPlans = em.createQuery(
                            "SELECT pp FROM PropertyPlan pp " +
                                    "JOIN pp.property p " +
                                    "WHERE p.id = :propertyId", PropertyPlan.class)
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

        return propertyPlans;
    }
}
