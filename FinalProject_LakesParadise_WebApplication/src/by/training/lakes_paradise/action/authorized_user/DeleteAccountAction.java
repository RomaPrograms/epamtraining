package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class handles authorized user request to delete account.
 */
public class DeleteAccountAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeleteAccountAction.class);

    /**
     * Method executes request to delete account.
     *
     * @param request  - user request
     * @param response - user response
     * @return action which should be executed after current request
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        HttpSession session = request.getSession();
        Forward forward
                = new Forward("/menu.html", true);
        Profile profile = (Profile) session.getAttribute("profile");
        session.removeAttribute("profile");

        UserService userService
                = factory.getService(UserService.class);
        userService.delete(profile.getId());
        LOGGER.info("User was successfully logged out");

        return forward;
    }
}
