package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReservedHomesteadsListAction extends Action{
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward;
        HttpSession session = request.getSession();
        session.setAttribute("lastAction", "/reserveHomestead.jsp");
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile != null) {
            forward = new Forward("/reserveHomestead.jsp", false);
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            List<Order> orders = factory.getService(OrderService.class)
                    .readByHomestead(homestead.getId());
            request.setAttribute("res", orders);
        } else {
            forward = new Forward("/home.html", true);
            forward.getAttributes().put("registerMessage", "You can't do this action while you didn't log in");
        }
        return forward;
    }
}
