package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuAction extends Action {

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response) {

        Forward forward
                = new Forward("/index.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/menu.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);

        return forward;
    }
}
