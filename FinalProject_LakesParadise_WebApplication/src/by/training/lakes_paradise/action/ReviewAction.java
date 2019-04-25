package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewAction extends Action{
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String string = request.getParameter("comment");
        factory.getService(ReviewService.class);
        return null;
    }
}
