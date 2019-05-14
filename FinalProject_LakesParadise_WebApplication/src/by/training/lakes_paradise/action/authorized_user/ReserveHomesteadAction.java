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
import java.util.Date;
import java.util.List;

/**
 * Class handles authorized user request for reservation homestead.
 */
public class ReserveHomesteadAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ReserveHomesteadInfoAction.class);

    /**
     * Method executes request for reservation homestead.
     *
     * @param request  - user request
     * @param response - user response
     * @return action which should be executed after current request
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward = new Forward(
                "/authorized_user/reservationInfo.html",
                true);
        HttpSession session = request.getSession();
        OrderValidator validator = (OrderValidator)
                ValidatorFactory.createValidator(Order.class);
        try {
            Order order = validator.validate(request);
            Homestead homestead
                    = (Homestead) session.getAttribute("homestead");
            if (dateValidation(order, homestead.getId())) {
                Profile profile = (Profile) session.getAttribute("profile");
                User user = new User();
                user.setId(profile.getId());
                order.setUser(user);
                order.setHomestead(homestead);
                OrderService orderService
                        = factory.getService(OrderService.class);
                orderService.create(order);
                forward.getAttributes().put("registerSuccessMessage",
                        "Congratulations, you have successfully registered!");
                LOGGER.info("Registration passed successfully");
            } else {
                forward.getAttributes().put("registerErrorMessage",
                        "Sorry, but some days from your chosen dates"
                                + " already took!");
            }
        } catch (IncorrectDataException e) {
            forward.getAttributes().put("registerErrorMessage",
                    "Date of end renting should be older than start renting");
        }
        return forward;
    }

    /**
     * Method validates is it possible to rent such dates.
     *
     * @param newOrder - new order
     * @param homesteadId - homestead id
     * @return {@code true} in condition that it is possible ant {@code false}
     * otherwise
     * @throws PersistentException - exception connected with DAO
     */
    private boolean dateValidation(final Order newOrder, final int homesteadId)
            throws PersistentException {
        List<Order> orders = factory.getService(OrderService.class)
                .readByHomestead(homesteadId);

        Date newStartRenting = newOrder.getStartRenting();
        Date newEndRenting = newOrder.getEndRenting();
        for (var order : orders) {

            Date startRenting = order.getStartRenting();
            Date endRenting = order.getEndRenting();

            if (newStartRenting.getTime() >= startRenting.getTime()
                    && newStartRenting.getTime() <= endRenting.getTime()) {
                return false;
            }

            if (newEndRenting.getTime() >= startRenting.getTime()
                    && newEndRenting.getTime() <= startRenting.getTime()) {
                return false;
            }

            if (newStartRenting.getTime() < startRenting.getTime()
                    && newEndRenting.getTime() > endRenting.getTime()) {
                return false;
            }
        }

        return true;
    }
}
