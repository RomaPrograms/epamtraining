package by.training.lakes_paradise.action.admin;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class executes request for finding users by login.
 */
public class FindUserByLoginAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindUserByLoginAction.class);

    /**
     * Method executes request for finding users by login.
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
        Forward forward = new Forward("/admin/usersList.html",
                true);
        UserService userService
                = factory.getService(UserService.class);
        String userLogin = request.getParameter("userLogin");
        List<User> users
                = userService.readByLogin(userLogin);
        forward.getAttributes().put("res", users);
        forward.getAttributes().put("userLogin", userLogin);
        LOGGER.info("Users were found successfully");

        return forward;
    }
}
