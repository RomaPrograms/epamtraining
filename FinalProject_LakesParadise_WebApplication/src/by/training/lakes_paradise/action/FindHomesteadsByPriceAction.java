package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class FindHomesteadsByPriceAction extends Action {
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        Forward forward = new Forward("/homesteadsList.html",
                true);
        HttpSession session = request.getSession(true);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        HomesteadService homesteadService
                = factory.getService(HomesteadService.class);
        String stringMinPrice = request.getParameter("minPrice");
        String stringMaxPrice = request.getParameter("maxPrice");

        BigDecimal minPrice = new BigDecimal(stringMinPrice);
        BigDecimal maxPrice = new BigDecimal(stringMaxPrice);
        List<Homestead> homesteads
                = homesteadService.readAllByPrice(minPrice, maxPrice);
        forward.getAttributes().put("res", homesteads);
        forward.getAttributes().put("minPrice", minPrice);
        forward.getAttributes().put("maxPrice", maxPrice);

        Config.set(request, Config.FMT_LOCALE, locale);

        return forward;
    }
}
