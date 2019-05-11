package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;
import java.util.Locale;

/**
 * Class handles authorized user request for showing reservation page.
 */
public class ReserveHomesteadInfoAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ReserveHomesteadInfoAction.class);

    /**
     * Method executes request for showing reservation page.
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
        Forward forward = new Forward(
                "/authorized_user/reserveHomestead.jsp",
                false);
        HttpSession session = request.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        if (profile != null) {
            Homestead homestead
                    = (Homestead) session.getAttribute("homestead");
            OrderService orderService = factory.getService(OrderService.class);
            List<Order> orders
                    = orderService.readByHomestead(homestead.getId());
            request.setAttribute("res", orders);
            Locale locale = (Locale) session.getAttribute("language");
            request.setAttribute("locale", locale);
            Config.set(request, Config.FMT_LOCALE, locale);
            LOGGER.info(
                    "Page for reserving homesteads was opened successfully");
        }

        return forward;
    }
}
