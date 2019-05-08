package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import by.training.lakes_paradise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;
import java.util.Locale;

/**
 * Class handles authorized user request for showing user cabinet page.
 */
public class UserCabinetAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserCabinetAction.class);

    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        Forward forward = new Forward(
                "/authorized_user/cabinetUser.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction",
                "/authorized_user/userCabinet.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        UserService userService = factory.getService(UserService.class);
        User user = userService.read(profile.getId());
        request.setAttribute("user", user);
        List<Order> orders = factory.getService(OrderService.class)
                .readByProfile(profile.getId());
        request.setAttribute("orders", orders);
        LOGGER.info("User cabinet was opened successfully");

        return forward;
    }
}
