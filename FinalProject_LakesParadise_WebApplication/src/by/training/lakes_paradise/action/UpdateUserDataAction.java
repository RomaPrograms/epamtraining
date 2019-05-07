package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ProfileService;
import by.training.lakes_paradise.service.UserService;
import by.training.lakes_paradise.validator.UserValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class UpdateUserDataAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/updateData.jsp", false);
        HttpSession session = request.getSession(true);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        Profile profile = (Profile) session.getAttribute("profile");
        User user = null;
        try {
            session.setAttribute("lastAction", "/updateData.html");
            request.setAttribute("profile", profile);
            UserValidator userValidator = (UserValidator)
                    ValidatorFactory.createValidator(User.class);
            user = userValidator.validate(request);
            user.setRole(profile.getRole());
            user.setId(profile.getId());
            factory.getService(UserService.class).update(user);
            request.setAttribute("successMessage", "You data were successfully updated.");
            profile = factory.getService(ProfileService.class).read(user.getId());
            profile.setId(user.getId());
            session.setAttribute("profile", profile);
            request.setAttribute("profile", profile);
        } catch (IncorrectDataException e) {
            user = factory.getService(UserService.class).read(profile.getId());
            user.setLogin(profile.getLogin());
            user.setPassword(profile.getPassword());
            request.setAttribute("userInfo", user);
        } catch (PersistentException e) {
            request.setAttribute("errorMessage", "Sorry, but profile with such login already exist, change your login please.");
            request.setAttribute("userInfo", user);
        }
        return forward;
    }
}
