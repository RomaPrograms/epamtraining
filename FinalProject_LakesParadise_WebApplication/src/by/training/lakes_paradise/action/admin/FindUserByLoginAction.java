package by.training.lakes_paradise.action.admin;

import by.training.lakes_paradise.action.FindHomesteadsByNameAction;
import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.service.ProfileService;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;
import java.util.Locale;

public class FindUserByLoginAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindUserByLoginAction.class);

    /**
     * Method executes request for finding homesteads.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward = new Forward("/admin/usersList.html",
                true);
        HttpSession session = request.getSession(true);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        ProfileService profileService
                = factory.getService(ProfileService.class);
        String userLogin = request.getParameter("userLogin");
        List<User> users
                = profileService.readByLogin(userLogin);
        request.setAttribute("res", users);

        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
