package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.validator.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInAction extends Action {
    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {
        Forward forward = null;
        try {
            HttpSession session = request.getSession(true);
            String string = (String) session.getAttribute("lastAction");
            forward = new Forward(string, true);
            ProfileValidator profileValidator = new ProfileValidator();
            Profile profile = profileValidator.validate(request);
            session.setAttribute("profile", profile);
        } catch (IncorrectDataException e) {
            request.setAttribute("logInMessage",
                    "Incorrect data were entered!");
        }
        return forward;
    }
}
