package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ProfileService;
import by.training.lakes_paradise.validator.ProfileValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class handles user request for authentication.
 */
public class LogInAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LogInAction.class);

    /**
     * Method executes request for authentication.
     *
     * @param request  - user request
     * @param response - user response
     * @return name of action which should be executed after current request
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward;
        HttpSession session = request.getSession(true);
        String lastAction = request.getHeader("referer");
        lastAction = lastAction.substring(lastAction.lastIndexOf('/'));
        forward = new Forward(lastAction, true);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        ResourceBundle resourceBundle = ResourceBundle
                .getBundle("property.text", locale);
        if (lastAction.equals("/sign_up.html")) {
            forward.setForwardUrl("/menu.html");
        }
        Profile profile;

        try {
            ProfileValidator profileValidator = (ProfileValidator)
                    ValidatorFactory.createValidator(Profile.class);
            ProfileService profileService
                    = factory.getService(ProfileService.class);
            profile = profileValidator.validate(request);
            profile = profileService
                    .read(profile.getLogin(), profile.getPassword());

            if (profile == null) {
                forward.getAttributes().put("logInMessage",
                        resourceBundle.getString("incorrectData"));
                LOGGER.info("Incorrect data. User doesn't exist.");
            } else {
                session.setAttribute("profile", profile);
                LOGGER.info("Authentication " + profile.getLogin()
                        + " passed successfully.");
            }

        } catch (IncorrectDataException e) {
            LOGGER.info("Incorrect data. User doesn't exist.");
        }

        return forward;
    }
}
