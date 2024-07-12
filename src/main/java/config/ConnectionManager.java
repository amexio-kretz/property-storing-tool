package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    public static EntityManagerFactory getEntityManagerFactory() {
        Map<String, Object> sourceProperties = new HashMap<>();
        sourceProperties.put("javax.persistence.nonJtaDataSource", DataSourceConfig.getDataSource());
        return Persistence.createEntityManagerFactory("source", sourceProperties);
    }




}
