package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;

public class ReserveHomesteadInfoAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward;
        HttpSession session = request.getSession();
        session.setAttribute("lastAction", "/authorized_user/reservationInfo.html");
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile != null) {
            forward = new Forward("/authorized_user/reserveHomestead.jsp", false);
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            List<Order> orders = factory.getService(OrderService.class)
                    .readByHomestead(homestead.getId());
            request.setAttribute("res", orders);
            Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        } else {
            forward = new Forward("/homesteadInfo.html", true);
            forward.getAttributes().put("registerMessage", "You can't do this action until you didn't log in");
        }
        return forward;
    }
}
