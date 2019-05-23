package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for validation order data from request.
 */
public class OrderValidator implements Validator<Order> {

    /**
     * String with start date for getting and saving start date.
     */
    private static final String START_DATE_STRING = "startDate";

    /**
     * Method for validation {@code Order} class.
     *
     * @param request - user request
     * @return Order class which will be created if validation will be passed
     * successfully
     * @throws IncorrectDataException - incorrect validation exception
     */
    @Override
    public Order validate(final HttpServletRequest request)
            throws IncorrectDataException {
        Order order = new Order();

        String startDate = request.getParameter(START_DATE_STRING);
        if (startDate != null && !startDate.isEmpty()) {
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(startDate);
                order.setStartRenting(date);
            } catch (ParseException e) {
                throw new IncorrectDataException(START_DATE_STRING, startDate);
            }
        } else {
            throw new IncorrectDataException(START_DATE_STRING, startDate);
        }

        String endDate = request.getParameter("endDate");
        if (endDate != null && !endDate.isEmpty()) {
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(endDate);
                order.setEndRenting(date);
            } catch (ParseException e) {
                throw new IncorrectDataException(START_DATE_STRING, startDate);
            }
        } else {
            throw new IncorrectDataException(START_DATE_STRING, startDate);
        }

        if (order.getStartRenting().getTime()
                > order.getEndRenting().getTime()) {
            throw new IncorrectDataException("endDate", endDate);
        }

        return order;
    }
}
