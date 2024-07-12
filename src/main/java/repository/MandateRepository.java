package repository;

import entity.MandateEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MandateRepository extends BaseRepository<MandateEntity> {
    public List<MandateEntity> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<MandateEntity> mandates;
        em.getTransaction().begin();

        try {
            mandates = em.createQuery(
                            "SELECT m FROM MandateEntity m " +
                                    "JOIN m.relatedProperty p " +
                                    "WHERE p.id = :propertyId", MandateEntity.class)
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

        return mandates;
    }
}
