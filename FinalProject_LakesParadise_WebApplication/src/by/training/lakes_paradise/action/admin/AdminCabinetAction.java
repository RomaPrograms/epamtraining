package by.training.lakes_paradise.action.admin;

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

/**
 * Class handles admin request for showing admin cabinet page
 */
public class AdminCabinetAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(AdminCabinetAction.class);

    /**
     * Method executes request for showing admin cabinet page.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {
        Forward forward = new Forward(
                "/admin/cabinetAdmin.jsp", false);
        HttpSession session = request.getSession(true);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        LOGGER.info("Admin cabinet was opened successfully");

        return forward;
    }
}
