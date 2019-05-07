package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteHomesteadAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/ownerHomesteads.html", true);
        int homesteadIdentity = Integer.parseInt(request.getParameter("homesteadIdentity"));
        factory.getService(HomesteadService.class).delete(homesteadIdentity);
        return forward;
    }
}
