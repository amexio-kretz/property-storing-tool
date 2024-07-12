package repository;

import config.ConnectionManager;

import javax.persistence.EntityManagerFactory;

public abstract class BaseRepository<T> {
    protected static EntityManagerFactory emf = ConnectionManager.getEntityManagerFactory();
}
