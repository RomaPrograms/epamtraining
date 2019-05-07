package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import by.training.lakes_paradise.validator.OrderValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReserveHomesteadAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/reserveHomestead.html", true);
        HttpSession session = request.getSession();
        OrderValidator validator = (OrderValidator)
                ValidatorFactory.createValidator(Order.class);
        try {
            Order order = validator.validate(request);
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            if (dateValidation(order, homestead.getId())) {
                Profile profile = (Profile) session.getAttribute("profile");
                request.setAttribute("profile", profile);
                User user = new User();
                user.setId(profile.getId());
                order.setUser(user);
                order.setHomestead(homestead);
                order.setPaid(true);
                factory.getService(OrderService.class).create(order);
                forward.getAttributes().put("registerSuccessMessage", "Congratulations, you have successfully registered!");
            } else {
                forward.getAttributes().put("registerErrorMessage", "Sorry, but some days from your chosen dates already took!");
            }
        } catch (IncorrectDataException e) {
            forward.getAttributes().put("registerErrorMessage", "Date of end renting should be older than start renting");
        }
        return forward;
    }


    public boolean dateValidation(Order newOrder, int homesteadId) throws PersistentException {
        List<Order> orders = factory.getService(OrderService.class).readByHomestead(homesteadId);

        for (var order : orders) {
            if (newOrder.getStartRenting().after(order.getStartRenting())
                    && newOrder.getStartRenting().before(order.getEndRenting())) {
                return false;
            }

            if (newOrder.getEndRenting().after(order.getStartRenting())
                    && newOrder.getStartRenting().before(order.getEndRenting())) {
                return false;
            }

            if (newOrder.getStartRenting().before(order.getStartRenting())
                    && newOrder.getStartRenting().after(order.getEndRenting())) {
                return false;
            }
        }

        return true;
    }
}
