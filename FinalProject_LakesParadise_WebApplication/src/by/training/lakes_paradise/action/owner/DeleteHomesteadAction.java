package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteHomesteadAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(DeleteHomesteadAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/owner/ownerHomesteads.html", true);

        int homesteadIdentity
                = Integer.parseInt(request.getParameter("homesteadIdentity"));
        factory.getService(HomesteadService.class).delete(homesteadIdentity);
        LOGGER.info("Homestead was deleted successfully");

        return forward;
    }
}
