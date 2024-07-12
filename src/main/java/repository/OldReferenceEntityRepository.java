package repository;

import entity.OldReferenceEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class OldReferenceEntityRepository extends BaseRepository<OldReferenceEntity> {
   public List<OldReferenceEntity> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<OldReferenceEntity> oldReferenceEntities;
        em.getTransaction().begin();

        try {
            oldReferenceEntities = em.createQuery(
                            "SELECT o FROM OldReferenceEntity o " +
                                    "JOIN o.relatedProperty p " +
                                    "WHERE p.id = :propertyId", OldReferenceEntity.class)
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

        return oldReferenceEntities;
    }
}
