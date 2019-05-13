package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class handles error situation with opening not available page.
 */
public class ErrorAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ErrorAction.class);

    /**
     * Method executes handles error situation with opening not available page.
     *
     * @param request  - user request
     * @param response - user response
     * @return name of action which should be executed after current request
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {
        Forward forward = new Forward("/menu.html", true);
        forward.getAttributes().put("incorrectPageMessage", "You didn't have"
                + " enough rules for opening last page "
                + ", try to log in for additional abilities.");
        LOGGER.error("Exception connected with opening unavailable page.");
        return forward;
    }
}
