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
import java.util.Map;

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
     * String with max price for getting and saving max price.
     */
    private static final String MAX_PRICE_STRING = "maxPrice";

    /**
     * String with min price for getting and saving min price.
     */
    private static final String MIN_PRICE_STRING = "minPrice";

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
        if (priceValidation(request, forward)) {
            Map<String, Object> map = forward.getAttributes();
            BigDecimal minPrice = (BigDecimal) map.get(MIN_PRICE_STRING);
            BigDecimal maxPrice = (BigDecimal) map.get(MAX_PRICE_STRING);
            List<Homestead> homesteads
                    = homesteadService.readAllByPrice(minPrice, maxPrice);
            forward.getAttributes().put("res", homesteads);
            LOGGER.info("Homesteads were found by price successfully");
        } else {
            forward.getAttributes().put("findByPriceErrorMessage",
                    "Enter min and max price or doesn't enter anything.");
        }

        return forward;
    }

    private boolean priceValidation(final HttpServletRequest request,
                                    final Forward forward) {
        String stringMinPrice = request.getParameter(MIN_PRICE_STRING);
        String stringMaxPrice = request.getParameter(MAX_PRICE_STRING);

        if (stringMaxPrice.isEmpty() && stringMinPrice.isEmpty()) {
            return true;
        }

        if ((!stringMaxPrice.isEmpty() && stringMinPrice.isEmpty())
                || (stringMaxPrice.isEmpty() && !stringMinPrice.isEmpty())) {
            return false;
        }

        BigDecimal minPrice = new BigDecimal(stringMinPrice);
        BigDecimal maxPrice = new BigDecimal(stringMaxPrice);
        forward.getAttributes().put(MIN_PRICE_STRING, minPrice);
        forward.getAttributes().put(MAX_PRICE_STRING, maxPrice);

        return minPrice.compareTo(maxPrice) <= 0;
    }
}