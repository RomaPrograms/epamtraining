package by.training.lakes_paradise.controller;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.ActionManager;
import by.training.lakes_paradise.action.ActionManagerFactory;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet class which handles WEB-requests.
 */
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
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    public ServiceFactory getFactory() throws PersistentException {
        return new ServiceFactoryRealization(new TransactionFactoryRealization());
    }

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
        Action action = (Action) request.getAttribute("action");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> attributes = (Map<String, Object>)
                        session.getAttribute("redirectedData");
                if (attributes != null) {
                    for (String key : attributes.keySet()) {
                        request.setAttribute(key, attributes.get(key));
                    }
                    session.removeAttribute("redirectedData");
                }
            }

            ActionManager actionManager
                    = ActionManagerFactory.getManager(getFactory());
            Forward forward
                    = actionManager.execute(action, request, response);
            actionManager.close();

            if (session != null && !forward.getAttributes().isEmpty()) {
                session.setAttribute("redirectedData",
                        forward.getAttributes());
            }

            String requestedUri = request.getRequestURI();
            if (forward.isRedirect()) {
                String redirectedUri
                        = request.getContextPath() + forward.getForward();
                LOGGER.debug(String.format("Request for URI \"%s\" id"
                                + " redirected to URI \"%s\"", requestedUri,
                        redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage = action.getName() + ".jsp";
                jspPage = "/jsp" + jspPage;

                LOGGER.debug(String.format("Request for URI \"%s\" is forwarded"
                        + " to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage)
                        .forward(request, response);
            }
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to process request", e);
            request.setAttribute("error", "Data processing error");
            getServletContext().getRequestDispatcher(
                    "/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
