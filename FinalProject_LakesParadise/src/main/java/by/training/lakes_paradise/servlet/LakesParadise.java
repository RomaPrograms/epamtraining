package by.training.lakes_paradise.servlet;

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
public class LakesParadise extends HttpServlet {
    /**
     * Method that handles GET requests.
     *
     * @param request  - request to servlet
     * @param response - response from servlet
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method that handles POST requests.
     *
     * @param request  - request to servlet
     * @param response - response from servlet
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest request,
                                final HttpServletResponse response) {

    }
}
