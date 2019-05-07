package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.validator.HomesteadValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class AddHomesteadAction extends Action {

    static int i = 0;

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/addHomestead.jsp", false);
        HttpSession session = request.getSession(true);
        if ( i==1 ) {
            i++;
        }
        i++;
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        Homestead homestead;
        HomesteadValidator homesteadValidator = (HomesteadValidator)
                ValidatorFactory.createValidator(Homestead.class);
        try {
            homestead = homesteadValidator.validate(request);
            User user = new User();
            user.setId(profile.getId());
            homestead.setOwner(user);
            factory.getService(HomesteadService.class).add(homestead);
            request.setAttribute("successMessage", "Homestead was successfully signed up.");
        } catch (IncorrectDataException e) {

        }
        return forward;
    }
}
