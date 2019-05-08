package by.training.lakes_paradise.action.owner;

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

public class OwnerHomesteadsListAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(OwnerHomesteadsListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/owner/ownerHomesteadsList.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/owner/ownerHomesteads.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        request.setAttribute("res", factory.getService(
                HomesteadService.class).readByOwner(profile.getId()));
        LOGGER.info("Owner homesteads list was shown successfully");

        return forward;
    }
}
