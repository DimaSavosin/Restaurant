package ru.kpfu.servlets.admin.adminReviews;


import ru.kpfu.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/reviewsDelete")
public class AdminDeleteReviewsServlets extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int reviewId = Integer.parseInt(req.getParameter("reviewId"));
            reviewService.deleteReviewById(reviewId);
            resp.sendRedirect(req.getContextPath() + "/admin/reviews");

    }
}

