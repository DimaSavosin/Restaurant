package ru.kpfu.servlets.controllers.user;


import ru.kpfu.servlets.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.servlets.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.servlets.service.ReviewService;

import javax.servlet.ServletConfig;
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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<ReviewResponseDTO> reviews = reviewService.getAllReviewsWithUserNames();
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/views/reviews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
