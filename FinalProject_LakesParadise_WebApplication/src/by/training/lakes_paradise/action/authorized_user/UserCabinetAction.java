package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import by.training.lakes_paradise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;

public class UserCabinetAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/authorized_user/cabinetUser.jsp", false);
        HttpSession session = request.getSession(true);
        session.setAttribute("lastAction", "/authorized_user/userCabinet.html");
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        User user = factory.getService(UserService.class).read(profile.getId());
        request.setAttribute("user", user);
        List<Order> orders = factory.getService(OrderService.class)
                .readByProfile(profile.getId());
        request.setAttribute("orders", orders);
        Config.set(request, Config.FMT_LOCALE, session.getAttribute("language"));
        return forward;
    }
}
