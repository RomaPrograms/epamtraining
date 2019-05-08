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

public class SignUpAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(SignUpAction.class);

    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        Forward forward = new Forward("/signUp.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/sign_up.html");
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
            user.setRole(Role.USER);
            factory.getService(UserService.class).create(user);
            request.setAttribute("successMessage", "You were successfully signed up, right now you can log in.");
            LOGGER.info("User " + user.getLogin() + " signed up successfully.");
        } catch (IncorrectDataException e) {
            LOGGER.error("User validation wasn't passed.");
        } catch (PersistentException e) {
            request.setAttribute("errorMessage", "Sorry, but profile with such login already exist, change your login please.");
            request.setAttribute("userInfo", user);
        }

        return forward;
    }
}
