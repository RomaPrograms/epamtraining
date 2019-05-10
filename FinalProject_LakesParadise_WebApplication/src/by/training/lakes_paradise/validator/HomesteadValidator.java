package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Class for validation homestead data from request.
 */
public class HomesteadValidator implements Validator<Homestead> {
    /**
     * Method for validation {@code Homestead} class.
     *
     * @param request - user request
     * @return Homestead class which will be created if validation will be
     * passed successfully
     * @throws IncorrectDataException - incorrect validation exception
     */
    @Override
    public Homestead validate(final HttpServletRequest request)
            throws IncorrectDataException {
        Homestead homestead = new Homestead();

        String title = request.getParameter("name");
        if (title != null && !title.isEmpty()) {
            homestead.setTitle(title);
        } else {
            throw new IncorrectDataException("name", title);
        }

        String description = request.getParameter("description");
        if (description != null && !description.isEmpty()) {
            homestead.setDescription(description);
        } else {
            throw new IncorrectDataException("description", description);
        }

        String peopleNumber = request.getParameter("peopleNumber");
        if (peopleNumber != null && !peopleNumber.isEmpty()) {
            homestead.setPeopleNumber(Integer.parseInt(peopleNumber));
        } else {
            throw new IncorrectDataException("peopleNumber", peopleNumber);
        }

        String price = request.getParameter("price");
        if (price != null && !price.isEmpty()) {
            homestead.setPrice(new BigDecimal(price));
        } else {
            throw new IncorrectDataException("price", price);
        }

        return homestead;
    }
}
