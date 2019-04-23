package by.training.lakes_paradise.controller;

import by.training.lakes_paradise.db.entity.Page;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet class which handles WEB-requests.
 */
@WebServlet("/firstAction")
public class DispatcherServlet extends HttpServlet {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DispatcherServlet.class);

    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;
    private static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;


    @Override
    public void init() throws ServletException {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
                    DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
                    DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    /*public ServiceFactory getFactory() throws PersistentException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }*/

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            action(request, response);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {
            action(request, response);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void action(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException, PersistentException {
        ServiceFactoryRealization factoryRealization = new ServiceFactoryRealization(new TransactionFactoryRealization());
        try {

            switch(Page.valueOf(request.getParameter("page").toUpperCase())) {
                case HOME:
                    break;
                case SIGNUP:
                    break;
                case HOMESTEAD:
                    request.setAttribute("res", factoryRealization.getService(HomesteadService.class).findAll());
                    request.getRequestDispatcher("jsp/homesteads.jsp").forward(
                            request, response);
            }
            /*Object string = request.getParameter("page");
            request.setAttribute("pushButton", string.toString());*/

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
