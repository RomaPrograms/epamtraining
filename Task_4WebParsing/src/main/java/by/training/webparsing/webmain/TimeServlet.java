package by.training.webparsing.webmain;

import by.training.webparsing.exception.ParsingException;
import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import by.training.webparsing.propertymanager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Servlet class which has methods for handling WEB-request.
 */
@WebServlet("/timeaction")
public class TimeServlet extends HttpServlet {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DevicesBuilderFactory.class);

    /**
     * Exception message for issues with file.
     */
    private static final String FILE_ISSUE = "Issue with file.";

    /**
     * Exception message for issues with servlet.
     */
    private static final String SERVLET_ISSUE = "Issue with servlet.";
    /**
     * Method that handles with GET requests.
     *
     * @param request  - request to servlet
     * @param response - response from servlet
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            LOGGER.error(SERVLET_ISSUE);
        } catch (IOException e) {
            LOGGER.error(FILE_ISSUE);
        }
    }

    /**
     * Method that handles with POST requests.
     *
     * @param request  - request to servlet
     * @param response - response from servlet
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            LOGGER.error(SERVLET_ISSUE);
        } catch (IOException e) {
            LOGGER.error(FILE_ISSUE);
        }
    }

    /**
     * Method that we will call in {@code doPost} and {@code doGet} methods for
     * handling with requests.
     *
     * @param request  - request to servlet
     * @param response - response from servlet
     * @throws ServletException - servlet can throw when it encounters
     *                          * difficulty.
     * @throws IOException      - exception which means problems with file.
     */
    private void processRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
            String typeOfParser = "";
            //String pathToPage = "";
            if (request.getParameter("button1") != null) {
                typeOfParser = "sax";
                /*pathToPage = "timeaction?button1=" + request
                        .getParameter("button1");*/
            } else {
                if (request.getParameter("button2") != null) {
                    typeOfParser = "dom";
                    /*pathToPage = "timeaction?button2=" + request
                            .getParameter("button2");*/
                } else {
                    if (request.getParameter("button3") != null) {
                        typeOfParser = "stax";
                        /*pathToPage = "timeaction?button3=" + request
                                .getParameter("button3");*/
                    } else {
                        String[] currentLang = request
                                .getParameter("lang").split("_");
                        String language = currentLang[0];
                        String country = currentLang[1];
                        ResourceManager resourceManager
                                = ResourceManager.INSTANCE;
                        resourceManager.changeResource(new Locale(
                                language, country));
                        request.setAttribute("lang",
                                resourceManager.getString("languageProp"));

                        request.getRequestDispatcher("index.jsp")
                                .forward(request, response);
                    }
                }
            }

            AbstractDeviceBuilder builder;

            builder = deviceFactory.createDeviceBuilder(typeOfParser);
            builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                    + "Task_4WebParsing\\src\\main\\resources\\data"
                    + "\\devices.xml");
            /*try {
                String[] currentLang = request
                        .getParameter("lang").split("_");
                String language = currentLang[0];
                String country = currentLang[1];
                ResourceManager resourceManager
                        = ResourceManager.INSTANCE;
                resourceManager.changeResource(new Locale(
                        language, country));
                request.setAttribute("lang",
                        resourceManager.getString("languageProp"));
            } catch (NullPointerException e) {
                LOGGER.info("Hasn't installed some language.");
            }*/

            /*request.setAttribute("path",
                    "http://localhost:8080/webParsingApp/" + pathToPage);*/
            request.setAttribute("res", builder.getDevices());
            request.getRequestDispatcher("/result.jsp").forward(
                    request, response);

        } catch (ParsingException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

