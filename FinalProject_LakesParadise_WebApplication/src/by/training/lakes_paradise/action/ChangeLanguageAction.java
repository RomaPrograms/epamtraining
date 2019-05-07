package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLanguageAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(true);
        String lastAction = (String) session.getAttribute("lastAction");
        Forward forward = new Forward(lastAction, true);

        String typeOfLanguage = request.getRequestURI();
        typeOfLanguage = typeOfLanguage.substring(10, typeOfLanguage.length() - 5);

        String[] currentLang = typeOfLanguage.split("_");
        String language = currentLang[0];
        String country = currentLang[1];
        Locale locale = new Locale(language, country);
        session.setAttribute("language", locale);

        return forward;
    }
}
