package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public class ProfileValidator implements Validator<Profile> {
    @Override
    public Profile validate(final HttpServletRequest request)
            throws IncorrectDataException {
        Profile profile = new Profile();

        String login = request.getParameter("login");
        if (login != null && !login.isEmpty()) {
            profile.setLogin(login);
        } else {
            throw new IncorrectDataException("login", login);
        }

        String password = request.getParameter("password");
        if (password != null && !password.isEmpty()) {
            profile.setPassword(password);
        } else {
            throw new IncorrectDataException("password", password);
        }

        return profile;
    }
}
