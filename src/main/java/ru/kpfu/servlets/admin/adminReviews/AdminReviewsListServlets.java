package ru.kpfu.servlets.admin.adminReviews;

import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/admin/reviews")
public class AdminReviewsListServlets extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<ReviewResponseDTO> reviews = reviewService.getAllReviewsWithUserNames();
            req.setAttribute("reviews", reviews);
            req.getRequestDispatcher("/WEB-INF/views/admin/adminReview.jsp").forward(req, resp);

    }
}
