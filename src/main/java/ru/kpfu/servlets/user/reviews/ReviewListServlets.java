package ru.kpfu.servlets.user.reviews;

import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewListServlets extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<ReviewResponseDTO> reviews = reviewService.getAllReviewsWithUserNames();
            double averageRating = reviewService.getAverageRating();

            req.setAttribute("averageRating", averageRating);
            req.setAttribute("reviews", reviews);

            req.getRequestDispatcher("/WEB-INF/views/reviews/reviews.jsp").forward(req, resp);

    }
}

