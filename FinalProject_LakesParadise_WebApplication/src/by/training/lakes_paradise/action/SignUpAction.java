package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.UserService;
import by.training.lakes_paradise.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// мне ещё здесь нужно добавить логгеры и обработку этих логеров


public class SignUpAction extends Action {

    static int i = 0;

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {

            UserValidator userValidator = new UserValidator();
            User user = userValidator.validate(request);
            HttpSession session = request.getSession(true);
            Integer roleId = (Integer)session.getAttribute("userRole");
            if (roleId == null) {
                user.setRole(Role.USER);
                factory.getService(UserService.class).create(user);
            } else {
                if (roleId == 2) {
                    user.setRole(Role.OWNER);
                    factory.getService(UserService.class).create(user);
                }
            }

            request.setAttribute("message", "Данные были успешно сохранены");
        } catch(IncorrectDataException e) {
            request.setAttribute("message", "Были обнаружены некорректно введённый данные");
        }

        return new Forward("/signUp.jsp", false);
    }
}
