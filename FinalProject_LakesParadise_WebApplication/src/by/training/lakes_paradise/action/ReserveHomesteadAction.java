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

public class ReserveHomesteadAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/reserveHomestead.html", true);
        HttpSession session = request.getSession();
        OrderValidator validator = (OrderValidator)
                ValidatorFactory.createValidator(Order.class);
        try {
            Order order = validator.validate(request);
            Profile profile = (Profile) session.getAttribute("profile");
            request.setAttribute("profile", profile);
            User user = new User();
            user.setId(profile.getId());
            order.setUser(user);
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            order.setHomestead(homestead);
            order.setPaid(true);
            factory.getService(OrderService.class).create(order);
        } catch (IncorrectDataException e) {
            forward.getAttributes().put("message", "Date of end renting should be older than start renting");
        }
        return forward;
    }
}
