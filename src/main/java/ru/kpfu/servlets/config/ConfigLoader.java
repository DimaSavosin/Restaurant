package ru.kpfu.servlets.config;
import ru.kpfu.servlets.util.DriverManagerDataSource;
import lombok.AllArgsConstructor;
import javax.sql.DataSource;
import java.util.Properties;
@AllArgsConstructor
public class ConfigLoader {
    private Properties properties;

    public DataSource customDatasource(){
        try {
            Class.forName(properties.getProperty("db.driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found: " + properties.getProperty("db.driverClassName"), e);
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        return dataSource;
    }

}