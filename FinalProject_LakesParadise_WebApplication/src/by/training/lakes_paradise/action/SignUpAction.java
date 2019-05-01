package by.training.lakes_paradise.action;

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

// мне ещё здесь нужно добавить логгеры и обработку этих логеров


public class SignUpAction extends Action {

    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/signUp.jsp");

        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("lastAction", "/signUp.html");
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profile", profile);
            UserValidator userValidator = (UserValidator)
                    ValidatorFactory.createValidator(User.class);
            User user = userValidator.validate(request);
            user.setRole(Role.USER);
            factory.getService(UserService.class).create(user);

            request.setAttribute("message",
                    "Данные были успешно сохранены");
        } catch (IncorrectDataException e) {
            request.setAttribute("message",
                    "Были обнаружены некорректно введённый данные");
        }

        return forward;
    }
}
