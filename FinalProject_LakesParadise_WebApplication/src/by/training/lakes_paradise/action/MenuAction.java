package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

/**
 * Class handles user request for showing menu page.
 */
public class MenuAction extends Action {

    /**
     * Method executes request for showing menu page.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward
                = new Forward("/index.jsp", false);
        HttpSession session = request.getSession(true);
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
