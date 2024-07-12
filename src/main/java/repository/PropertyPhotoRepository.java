package repository;

import entity.relation.property.PropertyPhoto;
import exception.StoringException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PropertyPhotoRepository extends BaseRepository<PropertyPhoto> {
    public List<PropertyPhoto> findByPropertyId(long propertyId) throws StoringException {
        EntityManager em = emf.createEntityManager();
        List<PropertyPhoto> propertyPhotos;
        em.getTransaction().begin();

        try {
            propertyPhotos = em.createQuery(
                            "SELECT pp FROM PropertyPhoto pp " +
                                    "JOIN pp.property p " +
                                    "WHERE p.id = :propertyId", PropertyPhoto.class)
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

        return propertyPhotos;
    }
}
