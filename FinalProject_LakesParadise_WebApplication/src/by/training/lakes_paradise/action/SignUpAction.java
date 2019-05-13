package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.UserService;
import by.training.lakes_paradise.validator.UserValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

/**
 * Class handles user request for registration.
 */
public class SignUpAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SignUpAction.class);

    /**
     * Method executes request for registration.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     */
    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        Forward forward = new Forward("/signUp.jsp", false);
        HttpSession session = request.getSession(true);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        User user = null;
        try {
            UserValidator userValidator = (UserValidator)
                    ValidatorFactory.createValidator(User.class);
            user = userValidator.validate(request);
            if (profile == null) {
                user.setRole(Role.USER);
            } else {
                user.setRole(Role.OWNER);
            }
            UserService userService = factory.getService(UserService.class);
            userService.create(user);
            request.setAttribute("successMessage",
                    "You were successfully signed up, right now you"
                            + " can log in.");
            LOGGER.info("User " + user.getLogin()
                    + " signed up successfully.");
        } catch (IncorrectDataException e) {
            LOGGER.error("User validation wasn't passed.");
        } catch (PersistentException e) {
            request.setAttribute("errorMessage", "Sorry, but profile with"
                    + " such login already exist, change your login please.");
            request.setAttribute("userInfo", user);
        }

        return forward;
    }
}
