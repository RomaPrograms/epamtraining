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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Servlet class which handles WEB-requests.
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DispatcherServlet.class);

    /**
     * Driver which is required for MySql database.
     */
    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * URL of the required database.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306"
            + "/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";

    /**
     * MySql root name of user.
     */
    private static final String DB_USER = "root";

    /**
     * Instance of {@code Locale} for setting language.
     */
    private static Locale locale = new Locale("en", "US");

    /**
     * Instance of {@code ResourceBundle} for getting password.
     */
    private static ResourceBundle rb = ResourceBundle
            .getBundle("property.text", locale);

    /**
     * MySql root password.
     */
    private static final String DB_PASSWORD = rb.getString("dbPassword");

    /**
     * String with redirectedData for getting and saving redirectedData.
     */
    private static final String REDIRECTED_DATA_STRING = "redirectedData";

    /**
     * Minimum size of accessible connections.
     */
    private static final int DB_POOL_START_SIZE = 10;

    /**
     * Maximum size of accessible connections.
     */
    private static final int DB_POOL_MAX_SIZE = 1000;

    /**
     * Method initializes connection poop.
     */
    @Override
    public void init() {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE);
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    /**
     * Method creates and initializes instance of
     * {@code ServiceFactoryRealization} class
     * which will be used for creating needed services.
     *
     * @return instance of {@code ServiceFactoryRealization} class
     * @throws PersistentException - exception connected with DAO
     */
    public ServiceFactory getFactory() throws PersistentException {
        return new ServiceFactoryRealization(
                new TransactionFactoryRealization());
    }

    /**
     * Method that processes GET requests.
     *
     * @param request  - user request
     * @param response - user response
     * @throws ServletException - exception connected with incorrect servlet
     * working
     * @throws IOException - exception connected with redirecting and
     * forwarding requests
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        action(request, response);
    }

    /**
     * Method that processes POST requests.
     *
     * @param request  - user request
     * @param response - user response
     * @throws ServletException - exception connected with incorrect servlet
     * working
     * @throws IOException - exception connected with redirecting and
     * forwarding requests
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        action(request, response);
    }

    /**
     * Method that calls all necessary methods for executing user action.
     *
     * @param request  - user request
     * @param response - user response
     * @throws ServletException - exception connected with incorrect servlet
     * working
     * @throws IOException - exception connected with redirecting and
     * forwarding requests
     */
    private void action(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws ServletException, IOException {
        Action action = (Action) request.getAttribute("action");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> attributes = (Map<String, Object>)
                        session.getAttribute(REDIRECTED_DATA_STRING);
                if (attributes != null) {
                    for (Map.Entry<String, Object> entry
                            : attributes.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        request.setAttribute(key, value);
                    }
                    session.removeAttribute(REDIRECTED_DATA_STRING);
                }
            }

            ActionManager actionManager
                    = ActionManagerFactory.getManager(getFactory());
            Forward forward
                    = actionManager.execute(action, request, response);
            actionManager.close();

            if (session != null && !forward.getAttributes().isEmpty()) {
                session.setAttribute(REDIRECTED_DATA_STRING,
                        forward.getAttributes());
            }

            String requestedUri = request.getRequestURI();
            if (forward.isRedirect()) {
                String redirectedUri
                        = request.getContextPath() + forward.getForwardUrl();
                LOGGER.debug(String.format("Request for URI \"%s\" id"
                                + " redirected to URI \"%s\"", requestedUri,
                        redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                jspPage = "/jsp" + forward.getForwardUrl();
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
