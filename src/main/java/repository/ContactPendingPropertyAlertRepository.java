package repository;

import entity.relation.contact.ContactPendingPropertyAlert;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ContactPendingPropertyAlertRepository extends BaseRepository<ContactPendingPropertyAlert> {

    public List<ContactPendingPropertyAlert> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<ContactPendingPropertyAlert> contactPendingPropertyAlerts = new ArrayList<>();
        em.getTransaction().begin();

        try {
            contactPendingPropertyAlerts = em.createQuery(
                            "SELECT a FROM ContactPendingPropertyAlert a " +
                                    "JOIN a.property p " +
                                    "WHERE p.id = :propertyId", ContactPendingPropertyAlert.class)
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

        return contactPendingPropertyAlerts;
    }
}
