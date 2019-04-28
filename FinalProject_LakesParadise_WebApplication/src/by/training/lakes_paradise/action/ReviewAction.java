package by.training.lakes_paradise.action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReviewAction extends Action{
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Review review = new Review();
        review.setText(request.getParameter("comment"));
        HttpSession session = request.getSession();
        Homestead homestead = (Homestead) session.getAttribute("homestead");
        review.setHomesteadId(homestead.getId());
        review.setDateOfComment(new Date().getTime());
        review.setUserName("Romka");
        factory.getService(ReviewService.class).create(review);
        homestead.getReviews().add(review);
        request.setAttribute("homestead", homestead);
        return new Forward("/home.jsp", false);
    }
}
