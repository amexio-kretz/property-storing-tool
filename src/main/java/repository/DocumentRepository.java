package repository;

import entity.DocumentEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository extends BaseRepository<DocumentEntity> {
   public List<DocumentEntity> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<DocumentEntity> documents;
        em.getTransaction().begin();

        try {
            documents = em.createQuery(
                            "SELECT d FROM DocumentEntity d " +
                                    "JOIN d.relatedProperty p " +
                                    "WHERE p.id = :propertyId", DocumentEntity.class)
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

        return documents;
    }
}
