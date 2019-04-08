package by.training.webparsing.webmain;

import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet class which has methods for handling WEB-request.
 */
@WebServlet("/timeaction")
public class TimeServlet extends HttpServlet {

    /**
     * Method that handles with GET requests.
     *
     * @param request - request to servlet
     * @param response - response from servlet
     * @throws ServletException - servlet can throw when it encounters
     * difficulty.
     * @throws IOException - exception which means problems with file.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method that handles with POST requests.
     *
     * @param request - request to servlet
     * @param response - response from servlet
     * @throws ServletException - servlet can throw when it encounters
     * difficulty.
     * @throws IOException - exception which means problems with file.
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method that we will call in {@code doPost} and {@code doGet} methods for
     * handling with requests.
     *
     * @param request - request to servlet
     * @param response - response from servlet
     * @throws ServletException - servlet can throw when it encounters
     *      * difficulty.
     * @throws IOException - exception which means problems with file.
     */
    protected void processRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServletException, IOException {
        DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
        String typeOfParser;
        if (request.getParameter("button1") != null) {
            typeOfParser = "sax";
        } else {
            if (request.getParameter("button2") != null) {
                typeOfParser = "dom";
            } else {
                typeOfParser = "stax";
            }
        }

        AbstractDeviceBuilder builder
                = deviceFactory.createDeviceBuilder(typeOfParser);
        builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                + "Task_4WebParsing\\src\\main\\resources\\data"
               + "\\devices.xml");

        request.setAttribute("res", builder.getDevices());
        request.getRequestDispatcher("/result.jsp").forward(
                request, response);
    }
}

