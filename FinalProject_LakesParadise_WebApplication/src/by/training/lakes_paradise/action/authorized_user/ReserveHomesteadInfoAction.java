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

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward;
        HttpSession session = request.getSession();
        session.setAttribute("lastAction",
                "/authorized_user/reservationInfo.html");
        Profile profile = (Profile) session.getAttribute("profile");

        if (profile != null) {
            forward = new Forward(
                    "/authorized_user/reserveHomestead.jsp",
                    false);
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
        } else {
            forward = new Forward("/homesteadInfo.html", true);
            forward.getAttributes().put("registerMessage",
                    "You can't do this action until you didn't log in");
        }

        return forward;
    }
}
