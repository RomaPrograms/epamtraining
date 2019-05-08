package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class HomesteadsListAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadsListAction.class);

    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/homesteadsList.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/homesteadsList.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);

        if (request.getAttribute("res") == null) {
            request.setAttribute("res", factory.getService(
                    HomesteadService.class).findAll());
            LOGGER.info("Homesteads list was shown successfully.");
        }

        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
