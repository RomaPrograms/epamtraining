package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ProfileService;
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
 * Class handles authorized user request for updating user info.
 */
public class UpdateUserInfoAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UpdateUserInfoAction.class);

    /**
     * Method executes request for updating user info.
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

        Forward forward = new Forward("/authorized_user/updateUser.jsp",
                false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction",
                "/authorized_user/updateUserInfo.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        User user = null;
        UserService userService = factory.getService(UserService.class);
        ProfileService profileService
                = factory.getService(ProfileService.class);
        try {
            UserValidator userValidator = (UserValidator)
                    ValidatorFactory.createValidator(User.class);
            user = userValidator.validate(request);
            user.setRole(profile.getRole());
            user.setId(profile.getId());

            userService.update(user);
            request.setAttribute("successMessage",
                    "You data were successfully updated.");
            profile = profileService.read(user.getId());
            profile.setId(user.getId());
            session.setAttribute("profile", profile);
            request.setAttribute("profile", profile);
            LOGGER.info("User data were successfully updated");
        } catch (IncorrectDataException e) {
            user = userService.read(profile.getId());
            user.setLogin(profile.getLogin());
            user.setPassword(profile.getPassword());
            request.setAttribute("userInfo", user);
            LOGGER.info("User validation wasn't passed");
        } catch (PersistentException e) {
            request.setAttribute("errorMessage",
                    "Sorry, but profile with such login already exist,"
                            + " change your login please.");
            request.setAttribute("userInfo", user);
        }

        return forward;
    }
}
