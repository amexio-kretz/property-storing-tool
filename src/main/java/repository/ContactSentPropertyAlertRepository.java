package repository;

import entity.relation.contact.ContactSentPropertyAlert;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ContactSentPropertyAlertRepository extends BaseRepository<ContactSentPropertyAlert> {
       public List<ContactSentPropertyAlert> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<ContactSentPropertyAlert> contactSentPropertyAlerts = new ArrayList<>();
        em.getTransaction().begin();

        try {
            contactSentPropertyAlerts = em.createQuery(
                            "SELECT a FROM ContactSentPropertyAlert a " +
                                    "LEFT JOIN FETCH a.contact " +
                                    "JOIN a.property p " +
                                    "WHERE p.id = :propertyId", ContactSentPropertyAlert.class)
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

        return contactSentPropertyAlerts;
    }
}
