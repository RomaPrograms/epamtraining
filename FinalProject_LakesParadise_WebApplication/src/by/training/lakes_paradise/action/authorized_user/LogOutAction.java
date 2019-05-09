package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class handles authorized user request to log out.
 */
public class LogOutAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LogOutAction.class);

    /**
     * Method executes request to log out.
     *
     * @param request  - user request
     * @param response - user response
     * @return action which should be executed after current request
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {

        HttpSession session = request.getSession();
        Forward forward
                = new Forward("/menu.html", true);
        session.removeAttribute("profile");
        LOGGER.info("User was successfully logged out");

        return forward;
    }
}
