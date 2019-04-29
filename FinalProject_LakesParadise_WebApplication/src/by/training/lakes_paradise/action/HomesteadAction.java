package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomesteadAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/home.jsp", false);
        int homeId = Integer.parseInt(request.getParameter("homesteadIdentity"));
        HttpSession session = request.getSession(true);
        session.setAttribute("action", "/home.html");
        String isLogIn = (String) session.getAttribute("isLogIn");
        if (isLogIn.equals("true")) {
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profileLogin", profile.getLogin());
        }
        Homestead homestead = factory.getService(HomesteadService.class).findById(homeId);
        homestead.setId(homeId);
        session.setAttribute("homestead", homestead);
        request.setAttribute("homestead", homestead);

        return forward;
    }
}
