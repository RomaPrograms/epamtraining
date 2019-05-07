package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OwnerHomesteadsListAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {

        Forward forward = new Forward("/owner/ownerHomesteadsList.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/owner/ownerHomesteads.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        request.setAttribute("res", factory.getService(
                HomesteadService.class).findByOwner(profile.getId()));

        return forward;
    }
}
