package by.training.lakes_paradise.action.admin;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ProfileService;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class handles admin request for deleting user.
 */
public class DeleteUserAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeleteUserAction.class);

    /**
     * Method executes request to delete user account.
     *
     * @param request  - user request
     * @param response - user response
     * @return action which should be executed after current request
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward = new Forward("/admin/usersList.html",
                true);
        UserService userService
                = factory.getService(UserService.class);
        ProfileService profileService
                = factory.getService(ProfileService.class);
        String stringUserIdentity = request.getParameter("userIdentity");
        int userIdentity = Integer.parseInt(stringUserIdentity);
        userService.delete(userIdentity);
        profileService.delete(userIdentity);
        //String login = (String) request.getAttribute("userLogin");
        //forward.getAttributes().put("userLogin", login);

        return forward;
    }
}
