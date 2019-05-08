package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
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

public class HomesteadInfoAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadInfoAction.class);

    @Override
    public Forward exec(
            HttpServletRequest request,
            HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/homesteadInfo.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/homesteadInfo.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        String stringHomesteadId = request.getParameter("homesteadIdentity");
        Homestead homestead;
        if (stringHomesteadId == null) {
            homestead = (Homestead) session.getAttribute("homestead");
        } else {
            int homesteadId = Integer.parseInt(stringHomesteadId);
            homestead = factory.getService(
                    HomesteadService.class).findById(homesteadId);
            homestead.setId(homesteadId);
        }

        session.setAttribute("homestead", homestead);
        request.setAttribute("homestead", homestead);
        LOGGER.info("Homestead " + homestead.getTitle()
                + "was showed successfully.");

        return forward;
    }
}
