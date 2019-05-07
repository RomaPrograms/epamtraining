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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

// мне ещё здесь нужно добавить логгеры и обработку этих логеров


public class SignUpAction extends Action {

    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        Forward forward = new Forward("/signUp.jsp", false);
        HttpSession session = request.getSession(true);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        User user = null;
        try {
            session.setAttribute("lastAction", "/sign_up.html");
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profile", profile);
            UserValidator userValidator = (UserValidator)
                    ValidatorFactory.createValidator(User.class);
            user = userValidator.validate(request);
            user.setRole(Role.USER);
            factory.getService(UserService.class).create(user);
            request.setAttribute("successMessage", "You were successfully signed up, right now you can log in.");
        } catch (IncorrectDataException e) {

        } catch (PersistentException e) {
            request.setAttribute("errorMessage", "Sorry, but profile with such login already exist, change your login please.");
            request.setAttribute("userInfo", user);
        }
        return forward;
    }
}
