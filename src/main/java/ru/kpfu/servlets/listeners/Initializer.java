package ru.kpfu.servlets.listeners;
import ru.kpfu.servlets.Repositories.*;
import ru.kpfu.servlets.Repositories.impl.BookingDAOImpl;
import ru.kpfu.servlets.Repositories.impl.UserDAOImpl;
import ru.kpfu.servlets.Repositories.impl.TableDAOImpl;
import ru.kpfu.servlets.config.ConfigLoader;
import ru.kpfu.servlets.service.BookingService;
import ru.kpfu.servlets.service.TableService;
import ru.kpfu.servlets.service.UserService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
        ConfigLoader configuration =
                new ConfigLoader(properties);
        ServletContextListener.super.contextInitialized(sce);


        UserDAO userDAO = new UserDAOImpl(configuration.customDatasource());
        TableDAO tableDAO = new TableDAOImpl(configuration.customDatasource());
        BookingDAO bookingDAO = new BookingDAOImpl(configuration.customDatasource());
        UserService userService = new UserService(userDAO);
        TableService tableService = new TableService(tableDAO);
        BookingService bookingService = new BookingService(bookingDAO);


        sce.getServletContext().setAttribute("userDAO", userDAO);
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("tableDAO",tableDAO);
        sce.getServletContext().setAttribute("tableService", tableService);
        sce.getServletContext().setAttribute("bookingService", bookingService);
    }
}
