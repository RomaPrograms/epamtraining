package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class MenuAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(MenuAction.class);

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {

        Forward forward
                = new Forward("/index.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/menu.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");

        if (locale == null) {
            locale = Locale.getDefault();
            session.setAttribute("language", locale);
        }

        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
