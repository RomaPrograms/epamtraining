package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.mysql.ImageDaoRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class MenuAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(ImageDaoRealization.class);

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {

        Forward forward
                = new Forward("/index.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/menu.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));

        return forward;
    }
}
