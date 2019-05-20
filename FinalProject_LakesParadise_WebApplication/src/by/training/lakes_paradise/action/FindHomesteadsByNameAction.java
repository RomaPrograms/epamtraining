package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class handles user request for finding homesteads by name.
 */
public class FindHomesteadsByNameAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindHomesteadsByNameAction.class);

    /**
     * Method executes request for finding homesteads by name.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward = new Forward("/homesteadsList.html",
                true);
        HomesteadService homesteadService
                = factory.getService(HomesteadService.class);
        String homesteadName = request.getParameter("homesteadName");
        List<Homestead> homesteads
                = homesteadService.readAllByTitle(homesteadName);
        forward.getAttributes().put("res", homesteads);
        forward.getAttributes().put("homesteadName", homesteadName);
        LOGGER.info("Homesteads were found by name successfully");

        return forward;
    }
}
