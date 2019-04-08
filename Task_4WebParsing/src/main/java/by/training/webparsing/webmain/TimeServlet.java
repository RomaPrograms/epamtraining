package by.training.webparsing.webmain;

import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.util.Calendar.LONG;

@WebServlet("/timeaction")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
        String typeOfParser;
        if(request.getParameter("button1") != null) {
            typeOfParser = "sax";
        } else {
            if(request.getParameter("button2") != null) {
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
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}

