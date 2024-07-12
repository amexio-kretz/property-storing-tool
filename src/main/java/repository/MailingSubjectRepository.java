package repository;

import entity.relation.mailing.MailingSubject;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MailingSubjectRepository extends BaseRepository<MailingSubject> {
   public List<MailingSubject> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<MailingSubject> mailings;
        em.getTransaction().begin();

        try {
            mailings = em.createQuery(
                            "SELECT m FROM MailingSubject m " +
                                    "JOIN m.property p " +
                                    "WHERE p.id = :propertyId", MailingSubject.class)
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

        return mailings;
    }
}
