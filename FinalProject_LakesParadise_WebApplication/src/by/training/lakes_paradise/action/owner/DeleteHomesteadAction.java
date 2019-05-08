package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class handles owner request for deleting homestead.
 */
public class DeleteHomesteadAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeleteHomesteadAction.class);

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward = new Forward("/owner/ownerHomesteads.html",
                true);

        String homesteadId = request.getParameter("homesteadIdentity");
        int homesteadIdentity = Integer.parseInt(homesteadId);
        HomesteadService homesteadService
                = factory.getService(HomesteadService.class);
        homesteadService.delete(homesteadIdentity);
        LOGGER.info("Homestead was deleted successfully");

        return forward;
    }
}
