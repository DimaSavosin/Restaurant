package ru.kpfu.servlets.user.reviews;


import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reviewsAdd")
public class AddReviewsServlets extends HttpServlet {
   private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    }
}
