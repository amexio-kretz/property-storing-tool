package repository;

import entity.PropertyEntity;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class PropertyRepository extends BaseRepository<PropertyEntity> {
    public List<PropertyEntity> readBetweenTwoDates(Date startDate, Date endDate) throws StoringException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<PropertyEntity> properties;

        try {
            properties = em.createQuery("SELECT p FROM PropertyEntity p " +
                    "WHERE p.dateVente >= :startDate AND p.dateVente <= :endDate",
                    PropertyEntity.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new StoringException(e.getCause());
        } finally {
            em.close();
        }

        return properties;
    }
}
