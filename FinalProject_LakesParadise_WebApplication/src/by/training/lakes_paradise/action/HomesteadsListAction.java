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
import java.util.List;
import java.util.Locale;

/**
 * Class handles user request for showing list of homesteads.
 */
public class HomesteadsListAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadsListAction.class);

    /**
     * Method executes request for showing list of homesteads.
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


        Forward forward = new Forward("/homesteadsList.jsp",
                false);
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        HomesteadService homesteadService
                = factory.getService(HomesteadService.class);

        if (request.getAttribute("res") == null) {
            List<Homestead> homesteadList = homesteadService.readAll();
            request.setAttribute("res", homesteadList);
            LOGGER.info("Homesteads list was shown successfully.");
        }

        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
