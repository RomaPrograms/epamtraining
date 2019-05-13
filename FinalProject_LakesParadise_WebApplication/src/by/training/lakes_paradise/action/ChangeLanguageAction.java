package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Class handles user request for changing language.
 */
public class ChangeLanguageAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ChangeLanguageAction.class);

    /**
     * Method executes request for changing language.
     *
     * @param request  - user request
     * @param response - user response
     * @return name of action which should be executed after current request
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        String lastAction = request.getHeader("referer");
        lastAction = lastAction.substring(
                lastAction.indexOf('/', 8));
        Forward forward = new Forward(lastAction, true);

        String typeOfLanguage = request.getRequestURI();
        typeOfLanguage = typeOfLanguage.substring(10,
                typeOfLanguage.length() - 5);

        String[] currentLang = typeOfLanguage.split("_");
        String language = currentLang[0];
        String country = currentLang[1];
        Locale locale = new Locale(language, country);
        session.setAttribute("language", locale);

        LOGGER.info("Language was changed successfully.");

        return forward;
    }
}
