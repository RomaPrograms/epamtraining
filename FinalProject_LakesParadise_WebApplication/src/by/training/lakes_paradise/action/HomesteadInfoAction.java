package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

/**
 * Class handles user request for showing detailed information about homestead.
 */
public class HomesteadInfoAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadInfoAction.class);

    /**
     * Method executes request for showing detailed information about homestead.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward = new Forward("/homesteadInfo.jsp",
                false);
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        String stringHomesteadId = request.getParameter("homesteadIdentity");
        Homestead homestead;
        HomesteadService homesteadService = factory.getService(
                HomesteadService.class);
        if (stringHomesteadId == null) {
            homestead = (Homestead) session.getAttribute("homestead");
        } else {
            int homesteadId = Integer.parseInt(stringHomesteadId);
            homestead = homesteadService.readById(homesteadId);
            homestead.setId(homesteadId);
        }

        session.setAttribute("homestead", homestead);
        request.setAttribute("homestead", homestead);
        LOGGER.info("Homestead " + homestead.getTitle()
                + "was showed successfully.");

        return forward;
    }
}
