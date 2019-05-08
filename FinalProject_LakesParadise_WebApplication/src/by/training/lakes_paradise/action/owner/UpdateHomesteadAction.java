package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.validator.HomesteadValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class UpdateHomesteadAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(UpdateHomesteadAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/owner/updateHomestead.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/owner/updateHomestead.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("locale"));

        Homestead homestead;
        HomesteadValidator homesteadValidator = (HomesteadValidator)
                ValidatorFactory.createValidator(Homestead.class);
        int homesteadId = Integer.parseInt(request.getParameter("homesteadIdentity"));

        try {
            homestead = homesteadValidator.validate(request);
            User user = new User();
            user.setId(profile.getId());
            homestead.setOwner(user);
            homestead.setId(homesteadId);
            factory.getService(HomesteadService.class).update(homestead);
            request.setAttribute("successMessage", "Homestead was successfully updated.");
            LOGGER.info("Homestead updating was passed successfully.");
        } catch (IncorrectDataException e) {
            homestead = factory.getService(HomesteadService.class).readById(homesteadId);
            homestead.setId(homesteadId);
            request.setAttribute("homestead", homestead);
            LOGGER.info("Homestead validation wasn't passed.");
        }

        return forward;
    }
}
