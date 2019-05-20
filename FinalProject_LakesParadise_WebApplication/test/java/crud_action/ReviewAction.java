package crud_action;

import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReviewAction {
    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"
            + "lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ReviewAction.class);

    private ReviewService reviewService;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private int createdReviewId;
    private Review review1 = new Review();
    private Review review2 = new Review();
    private Review review3 = new Review();
    private Review review4 = new Review();
    private Review review5 = new Review();
    private Review review6 = new Review();
    private Review review7 = new Review();
    private Review review8 = new Review();
    private Review review9 = new Review();

    @BeforeClass
    public void initialiseParsingAction() {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE);

            TransactionFactoryRealization transaction
                    = new TransactionFactoryRealization();
            ServiceFactory serviceFactory
                    = new ServiceFactoryRealization(transaction);
            reviewService
                    = serviceFactory.getService(ReviewService.class);
            review1.setId(1);
            review1.setText("review1");
            review1.setUserName("Pasha");
            review1.setDateOfComment(format.parse("2000-11-11"));
            review1.setHomesteadId(1);

            review2.setId(2);
            review2.setText("review2");
            review2.setUserName("Gennadi");
            review2.setDateOfComment(format.parse("2001-11-11"));
            review2.setHomesteadId(2);

            review3.setId(3);
            review3.setText("review3");
            review3.setUserName("Slava");
            review3.setDateOfComment(format.parse("2002-11-11"));
            review3.setHomesteadId(3);

            review4.setId(4);
            review4.setText("review1");
            review4.setUserName("Ludmila");
            review4.setDateOfComment(format.parse("2003-11-11"));
            review4.setHomesteadId(4);

            review5.setId(5);
            review5.setText("review2");
            review5.setUserName("Gennadi");
            review5.setDateOfComment(format.parse("2004-11-11"));
            review5.setHomesteadId(3);

            review6.setId(6);
            review6.setText("review3");
            review6.setUserName("Slava");
            review6.setDateOfComment(format.parse("2005-11-11"));
            review6.setHomesteadId(2);

            review7.setId(7);
            review7.setText("review1");
            review7.setUserName("Pasha");
            review7.setDateOfComment(format.parse("2006-11-11"));
            review7.setHomesteadId(3);

            review8.setId(8);
            review8.setText("review2");
            review8.setUserName("Ludmila");
            review8.setDateOfComment(format.parse("2007-11-11"));
            review8.setHomesteadId(2);

        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
        } catch (ParseException e) {
            LOGGER.error("Incorrect data were entered", e);
        }
    }

    @DataProvider()
    public Object[] dataProviderForReadReviewByIdAction() {
        return new Object[][]{{review2, 2}, {review4, 4}, {review6, 6}};
    }


    @Test(dataProvider = "dataProviderForReadReviewByIdAction")
    public void readReviewByIdAction(Review expectedReview, int id) {
        try {
            Review actualReview = reviewService.read(id);
            Assert.assertEquals(actualReview, expectedReview);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Object[] dataProviderForReadReviewsByHomesteadIdAction() {
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(review1);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(review7);
        reviews2.add(review5);
        reviews2.add(review3);
        return new Object[][]{{reviews1, 1}, {reviews2, 3}};
    }


    @Test(dataProvider = "dataProviderForReadReviewsByHomesteadIdAction",
            priority = 1)
    public void readReviewsByHomesteadIdAction(List<Review> expectedReviews,
                                               int id) {
        try {
            List<Review> actualReviews = reviewService.readReviewsByHomeId(id);
            Assert.assertEquals(actualReviews, expectedReviews);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Object[] dataProviderForCreateReviewAction() {
        try {
            review9.setDateOfComment(format.parse("2008-11-11"));
            review9.setText("review10");
            review9.setUserName("Slava");
            review9.setHomesteadId(4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Object[]{review9};
    }


    @Test(dataProvider = "dataProviderForCreateReviewAction", priority = 2)
    public void createReviewAction(Review expectedReview) {
        try {
            int reviewId = reviewService.create(expectedReview);
            createdReviewId = reviewId;
            Review actualReview = reviewService.read(reviewId);
            expectedReview.setId(reviewId);
            Assert.assertEquals(actualReview, expectedReview);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Object[] dataProviderForUpdateReviewAction() {
        try {
            review9.setId(createdReviewId);
            review9.setDateOfComment(format.parse("2009-11-11"));
            review9.setText("review11");
            review9.setUserName("Ludmila");
            review9.setHomesteadId(3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Object[][]{{review9, createdReviewId}};
    }


    @Test(dataProvider = "dataProviderForUpdateReviewAction", priority = 3)
    public void updateReviewAction(Review expectedReview, int id) {
        try {
            reviewService.update(expectedReview);
            Review actualReview = reviewService.read(id);
            Assert.assertEquals(actualReview, expectedReview);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Object[] dataProviderForDeleteReviewAction() {
        return new Object[]{createdReviewId};
    }


    @Test(dataProvider = "dataProviderForDeleteReviewAction", priority = 4)
    public void deleteReviewAction(int id) {
        try {
            reviewService.delete(id);
            Review actualReview = reviewService.read(id);
            Assert.assertNull(actualReview);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

}
