package ru.kpfu.controllers.user;


import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
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
public class ReviewsServlets extends HttpServlet {
   private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviewsWithUserNames();
        double averageRating = reviewService.getAverageRating();
        req.setAttribute("averageRating", averageRating);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/views/reviews.jsp").forward(req, resp);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        int userId = (int) req.getSession().getAttribute("userId");
        int rating = Integer.parseInt(req.getParameter("rating"));
        String comment = req.getParameter("comment");

        ReviewRequestDTO reviewRequestDTO = ReviewRequestDTO.builder()
                .userId(userId)
                .rating(rating)
                .comment(comment)
                .build();

        reviewService.addReview(reviewRequestDTO);
        resp.sendRedirect(req.getContextPath() + "/reviews");
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
