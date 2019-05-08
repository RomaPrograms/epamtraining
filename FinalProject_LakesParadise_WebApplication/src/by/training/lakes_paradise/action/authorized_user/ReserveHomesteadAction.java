package by.training.lakes_paradise.action.authorized_user;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import by.training.lakes_paradise.validator.OrderValidator;
import by.training.lakes_paradise.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReserveHomesteadAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ReserveHomesteadInfoAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward(
                "/authorized_user/reservationInfo.html", true);
        HttpSession session = request.getSession();
        OrderValidator validator = (OrderValidator)
                ValidatorFactory.createValidator(Order.class);
        try {
            Order order = validator.validate(request);
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            if (dateValidation(order, homestead.getId())) {
                Profile profile = (Profile) session.getAttribute("profile");
                User user = new User();
                user.setId(profile.getId());
                order.setUser(user);
                order.setHomestead(homestead);
                order.setPaid(true);
                factory.getService(OrderService.class).create(order);
                forward.getAttributes().put("registerSuccessMessage", "Congratulations, you have successfully registered!");
                LOGGER.info("Registration passed successfully");
            } else {
                forward.getAttributes().put("registerErrorMessage", "Sorry, but some days from your chosen dates already took!");
            }
        } catch (IncorrectDataException e) {
            forward.getAttributes().put("registerErrorMessage", "Date of end renting should be older than start renting");
        }
        return forward;
    }


    private boolean dateValidation(Order newOrder, int homesteadId)
            throws PersistentException {
        List<Order> orders = factory.getService(OrderService.class)
                .readByHomestead(homesteadId);

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
