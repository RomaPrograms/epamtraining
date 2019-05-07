package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class HomesteadListAction extends Action {
    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/homesteadsList.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/homesteads.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        if (request.getAttribute("res") == null) {
            request.setAttribute("res", factory.getService(
                    HomesteadService.class).findAll());
        }
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));

        return forward;
    }
}
