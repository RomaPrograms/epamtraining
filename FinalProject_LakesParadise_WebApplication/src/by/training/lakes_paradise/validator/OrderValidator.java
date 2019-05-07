package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderValidator implements Validator<Order> {
    @Override
    public Order validate(HttpServletRequest request) throws IncorrectDataException {
        Order order = new Order();

        String startDate = request.getParameter("startDate");
        if (startDate != null && !startDate.isEmpty()) {
            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date date = format.parse(startDate);
                order.setStartRenting(date);
            } catch (ParseException e) {
                throw new IncorrectDataException("startDate", startDate);
            }
        } else {
            throw new IncorrectDataException("startDate", startDate);
        }

        String endDate = request.getParameter("endDate");
        if (endDate != null && !endDate.isEmpty()) {
            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date date = format.parse(endDate);
                order.setEndRenting(date);
            } catch (ParseException e) {
                throw new IncorrectDataException("startDate", startDate);
            }
        } else {
            throw new IncorrectDataException("startDate", startDate);
        }

        if(order.getStartRenting().getTime()
                > order.getEndRenting().getTime()) {
            throw new IncorrectDataException("endDate", endDate);
        }

        return order;
    }
}
