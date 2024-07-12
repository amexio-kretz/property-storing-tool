package repository;

import entity.ImageEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ImageRepository extends BaseRepository<ImageEntity> {
  public List<ImageEntity> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<ImageEntity> images;
        em.getTransaction().begin();

        try {
            images = em.createQuery(
                            "SELECT i FROM ImageEntity i " +
                                    "JOIN i.relatedProperty p " +
                                    "WHERE p.id = :propertyId", ImageEntity.class)
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

        return images;
    }
}
