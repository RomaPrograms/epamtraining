package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String string = request.getParameter("homesteadIdentity");
        int homeId = Integer.parseInt(request.getParameter("homesteadIdentity"));
        Homestead homestead = factory.getService(HomesteadService.class).findById(homeId);
        request.setAttribute("homestead", homestead);
        return new Forward("/home.jsp", false);
    }
}
