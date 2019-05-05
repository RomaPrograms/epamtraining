package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReviewAction extends Action {
    @Override
    public Forward exec(
            final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(true);
        Forward forward = new Forward("/home.html", true);
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile == null) {

        } else {
            Review review = new Review();
            review.setText(request.getParameter("comment"));
            Homestead homestead = (Homestead) session.getAttribute("homestead");
            review.setHomesteadId(homestead.getId());
            review.setDateOfComment(new Date());
            review.setUserName(profile.getLogin());
            factory.getService(ReviewService.class).create(review);
            homestead.getReviews().add(0, review);
            session.setAttribute("homestead", homestead);
        }

        return forward;
    }
}
