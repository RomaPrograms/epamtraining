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

/**
 * Class handles user request for authentication.
 */
public class LogInAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LogInAction.class);

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward;
        HttpSession session = request.getSession(true);
        String lastAction = (String) session.getAttribute("lastAction");
        forward = new Forward(lastAction, true);
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
                        "Such profile doesn't exist!");
                LOGGER.info("Incorrect data. User doesn't exist.");
            } else {
                session.setAttribute("profile", profile);
                LOGGER.info("Authentication " + profile.getLogin()
                        + " passed successfully.");
            }

        } catch (IncorrectDataException e) {
            forward.getAttributes().put("logInMessage",
                    "Incorrect data were entered!");
            LOGGER.info("Incorrect data. User doesn't exist.");
        }

        return forward;
    }
}
