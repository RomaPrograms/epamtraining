package by.training.lakes_paradise.action.admin;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

/**
 * Class executes request for showing list of users.
 */
public class UsersListAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UsersListAction.class);

    /**
     * Method executes request for showing list of users.
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
        Forward forward = new Forward("/admin/usersList.jsp",
                false);
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        UserService userService
                = factory.getService(UserService.class);

        if(request.getAttribute("res") == null) {
            request.setAttribute("res", userService.readAll());
            LOGGER.info("Users list was shown successfully.");
        }

        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
