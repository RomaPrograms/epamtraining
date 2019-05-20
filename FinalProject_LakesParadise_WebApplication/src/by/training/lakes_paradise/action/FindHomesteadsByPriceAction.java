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
import java.math.BigDecimal;
import java.util.List;

/**
 * Class handles user request for finding homesteads by price.
 */
public class FindHomesteadsByPriceAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindHomesteadsByPriceAction.class);

    /**
     * Method executes request for finding homesteads by price.
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
        String stringMinPrice = request.getParameter("minPrice");
        String stringMaxPrice = request.getParameter("maxPrice");

        BigDecimal minPrice = new BigDecimal(stringMinPrice);
        BigDecimal maxPrice = new BigDecimal(stringMaxPrice);
        List<Homestead> homesteads
                = homesteadService.readAllByPrice(minPrice, maxPrice);
        forward.getAttributes().put("res", homesteads);
        forward.getAttributes().put("minPrice", minPrice);
        forward.getAttributes().put("maxPrice", maxPrice);
        LOGGER.info("Homesteads were found by price successfully");

        return forward;
    }
}
