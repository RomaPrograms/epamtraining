package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class HomesteadInfoAction extends Action {

    @Override
    public Forward exec(
            HttpServletRequest request,
            HttpServletResponse response) throws PersistentException {

        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/homesteadInfo.html");
        Forward forward = new Forward("/homesteadInfo.jsp", false);
        String stringHomesteadId = request.getParameter("homesteadIdentity");
        Homestead homestead;
        int homesteadId;
        if (stringHomesteadId == null) {
            homestead = (Homestead) session.getAttribute("homestead");
        } else {
            homesteadId = Integer.parseInt(stringHomesteadId);
            homestead = factory.getService(
                    HomesteadService.class).findById(homesteadId);
            homestead.setId(homesteadId);
        }

        session.setAttribute("homestead", homestead);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        request.setAttribute("homestead", homestead);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));

        return forward;
    }
}