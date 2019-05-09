package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for validation user data from request.
 */
public class UserValidator implements Validator<User> {

    /**
     * Method for validation {@code User} class.
     *
     * @param request - user request
     * @return User class which will be created if validation will be passed
     * successfully
     * @throws IncorrectDataException - incorrect validation exception
     */
    @Override
    public User validate(final HttpServletRequest request)
            throws IncorrectDataException {
        User user = new User();

        String login = request.getParameter("login");
        if (login != null && !login.isEmpty()) {
            user.setLogin(login);
        } else {
            throw new IncorrectDataException("login", login);
        }

        String password = request.getParameter("password");
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        } else {
            throw new IncorrectDataException("password", password);
        }

        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        } else {
            throw new IncorrectDataException("name", name);
        }

        String surname = request.getParameter("surname");
        if (surname != null && !surname.isEmpty()) {
            user.setSurname(surname);
        } else {
            throw new IncorrectDataException("surname", surname);
        }

        String phone = request.getParameter("phoneNumber");
        try {
            user.setPhone(Long.parseLong(phone));
        } catch (NumberFormatException e) {
            throw new IncorrectDataException("phoneNumber", phone);
        }

        return user;
    }
}
