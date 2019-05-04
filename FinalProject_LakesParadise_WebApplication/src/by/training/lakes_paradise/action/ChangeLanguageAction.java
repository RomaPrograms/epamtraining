package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageAction extends Action{
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = null;
        HttpSession session = request.getSession(true);
        String lastAction = (String) session.getAttribute("lastAction");
        forward = new Forward(lastAction, true);

        return forward;
    }
}
