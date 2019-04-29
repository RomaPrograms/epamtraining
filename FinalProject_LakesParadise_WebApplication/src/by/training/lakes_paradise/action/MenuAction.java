package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuAction extends Action {

    static int i = 0;

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {

        Action.Forward forward = new Action.Forward("/index.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("action", "/menu.html");
        String isLogIn = (String) session.getAttribute("isLogIn");
        if (isLogIn.equals("true")) {
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profileLogin", profile.getLogin());
        }

        return forward;
    }
}
