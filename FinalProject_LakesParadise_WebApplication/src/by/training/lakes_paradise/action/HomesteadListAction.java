package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomesteadListAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request,
                        HttpServletResponse response) throws PersistentException {
        request.setAttribute("res", factory.getService(HomesteadService.class).findAll());

        return new Action.Forward("/homesteads.jsp", false);
    }
}
