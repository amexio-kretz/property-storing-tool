package repository;

import entity.VisitEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class VisitRepository extends BaseRepository<VisitEntity> {
    public List<VisitEntity> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<VisitEntity> visits;

        try {
            visits = em.createQuery(
                    "SELECT v FROM VisitEntity v " +
                            "JOIN v.relatedProperty p " +
                            "WHERE p.id = :propertyId", VisitEntity.class)
                    .setParameter("propertyId", propertyId)
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

        return visits;
    }
}
