package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.sql.DataSource;

public class DataSourceConfig {
    private static HikariDataSource dataSource;

    static {
        loadConfig("application.properties");
    }

    public static void loadConfig(String configFilePath)  {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration(configFilePath);

            HikariConfig sourceConfig = new HikariConfig();
            sourceConfig.setJdbcUrl(config.getString("db.url"));
            sourceConfig.setUsername(config.getString("db.username"));
            sourceConfig.setPassword(config.getString("db.password"));
            sourceConfig.setDriverClassName("org.postgresql.Driver");
            sourceConfig.setMaximumPoolSize(10);
            sourceConfig.setMinimumIdle(5);
            sourceConfig.setIdleTimeout(30000);
            sourceConfig.setConnectionTimeout(30000);
            sourceConfig.setMaxLifetime(1800000);
            dataSource = new HikariDataSource(sourceConfig);

        } catch (Exception e) {
            System.out.println("Can't connect to database. Check app.log");
            System.exit(1);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}
