package ru.kpfu.servlets.listeners;
import ru.kpfu.servlets.Repositories.*;
import ru.kpfu.servlets.Repositories.impl.*;

import ru.kpfu.servlets.service.*;
import ru.kpfu.servlets.util.DriverManagerDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Initializer implements ServletContextListener {
    private static final String DB_DRIVER = "org.postgresql.Driver";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ORIS";

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "vimer_401";

    public void contextInitialized(ServletContextEvent sce) {


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriver(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);



        UserDAO userDAO = new UserDAOImpl(dataSource);
        TableDAO tableDAO = new TableDAOImpl(dataSource);
        BookingDAO bookingDAO = new BookingDAOImpl(dataSource);
        MenuDAO menuDAO = new MenuDAOImpl(dataSource);
        FavoriteTableDAO favoriteTableDAO = new FavoriteTableDAOImpl(dataSource);
        FileDAO fileDAO = new FileDAOImpl(dataSource);
        ReviewDAO reviewDAO = new ReviewDAOImpl(dataSource);




        UserService userService = new UserService(userDAO);
        TableService tableService = new TableService(tableDAO);
        BookingService bookingService = new BookingService(bookingDAO);
        MenuService menuService = new MenuService(menuDAO);
        FavoriteTableService favoriteTableService = new FavoriteTableService(favoriteTableDAO);
        FileService fileService = new FileService(fileDAO);
        ReviewService reviewService = new ReviewService(reviewDAO);



        sce.getServletContext().setAttribute("userDAO", userDAO);
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("tableDAO",tableDAO);
        sce.getServletContext().setAttribute("tableService", tableService);
        sce.getServletContext().setAttribute("bookingService", bookingService);
        sce.getServletContext().setAttribute("menuService", menuService);
        sce.getServletContext().setAttribute("favoriteTableService", favoriteTableService);
        sce.getServletContext().setAttribute("fileService", fileService);
        sce.getServletContext().setAttribute("reviewService", reviewService);
    }
}
