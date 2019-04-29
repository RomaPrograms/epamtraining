package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomesteadListAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request,
                        HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/homesteads.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("action", "/homesteads.html");
        String isLogIn = (String) session.getAttribute("isLogIn");
        if (isLogIn.equals("true")) {
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profileLogin", profile.getLogin());
        }
        request.setAttribute("res", factory.getService(HomesteadService.class).findAll());

        return forward;
    }
}
