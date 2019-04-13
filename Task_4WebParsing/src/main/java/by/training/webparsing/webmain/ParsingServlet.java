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
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * Servlet class which has methods for handling WEB-request.
 */
@WebServlet("/timeaction")
public class ParsingServlet extends HttpServlet {

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
     * Type of parser which we chose.
     */
    private static String typeOfParser;

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
            ResourceManager resourceManager
                    = ResourceManager.INSTANCE;

            setTypeOfParser(request);
            if (request.getParameter("lang") != null) {
                String[] currentLang = request
                        .getParameter("lang").split("_");
                String language = currentLang[0];
                String country = currentLang[1];
                resourceManager.changeResource(new Locale(
                        language, country));
                request.setAttribute("lang",
                        resourceManager.getString("languageProp"));
                request.getRequestDispatcher("index.jsp")
                        .forward(request, response);
            }

            AbstractDeviceBuilder builder;

            builder = deviceFactory.createDeviceBuilder(typeOfParser);
            builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                    + "Task_4WebParsing\\src\\main\\resources\\data"
                    + "\\devices.xml");

            String typeOfLanguage;
            if (request.getParameter("chooser") != null) {
               typeOfLanguage = request.getParameter("chooser");
            } else {
                typeOfLanguage = "en_EN";
            }

            String[] currentLang = typeOfLanguage.split("_");
            String language = currentLang[0];
            String country = currentLang[1];
            Locale locale = new Locale(language, country);
            Config.set(request, Config.FMT_LOCALE, locale);

            request.setAttribute("res", builder.getDevices());
            request.getRequestDispatcher("/result.jsp").forward(
                    request, response);

        } catch (ParsingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * method sets type of parser when we change it.
     * @param request - request to servlet
     */
    public static void setTypeOfParser(final HttpServletRequest request) {
        if (request.getParameter("button1") != null) {
            typeOfParser = request.getParameter("button1");
        }
    }
}

