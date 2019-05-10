package by.training.lakes_paradise.action.admin;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;
import java.util.Locale;

public class DeleteReviewAction extends Action {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeleteReviewAction.class);

    /**
     * Method executes request for deleting incorrect comments.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {

        HttpSession session = request.getSession(true);
        String lastAction = request.getHeader("referer");
        lastAction = lastAction.substring(lastAction.lastIndexOf('/'));
        Forward forward = new Forward(lastAction, true);
        Profile profile = (Profile) session.getAttribute("profile");
        request.setAttribute("profile", profile);
        Locale locale = (Locale) session.getAttribute("language");
        request.setAttribute("locale", locale);
        Config.set(request, Config.FMT_LOCALE, locale);

        String reviewIdentity = request.getParameter("reviewIdentity");
        int reviewId = Integer.parseInt(reviewIdentity);
        ReviewService reviewService = factory.getService(ReviewService.class);
        reviewService.delete(reviewId);
        Homestead homestead = (Homestead) session.getAttribute("homestead");
        List<Review> reviewList = homestead.getReviews();
        for(int i = 0; i < reviewList.size(); i++) {
            if(reviewList.get(i).getId() == reviewId) {
                reviewList.remove(i);
                break;
            }
        }

        LOGGER.info("Review was deleted successfully");

        return forward;
    }
}
