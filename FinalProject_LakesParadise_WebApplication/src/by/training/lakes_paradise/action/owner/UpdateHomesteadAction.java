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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class UpdateHomesteadAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/owner/updateHomestead.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/owner/updateHomestead.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
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
        } catch (IncorrectDataException e) {
            homestead = factory.getService(HomesteadService.class).findById(homesteadId);
            homestead.setId(homesteadId);
            request.setAttribute("homestead", homestead);
        }
        return forward;
    }
}