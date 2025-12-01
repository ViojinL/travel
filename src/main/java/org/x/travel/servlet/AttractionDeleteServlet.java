package org.x.travel.servlet;

import java.io.IOException;

import org.x.travel.entity.Attraction;
import org.x.travel.entity.User;
import org.x.travel.service.AttractionService;
import org.x.travel.service.impl.AttractionServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/attraction/delete")
public class AttractionDeleteServlet extends HttpServlet {
    private final AttractionService attractionService = new AttractionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Attraction attraction = attractionService.getDetail(id);
        String cityIdParam = req.getParameter("cityId");
        int cityId = 0;
        if (cityIdParam != null) {
            try {
                cityId = Integer.parseInt(cityIdParam);
            } catch (NumberFormatException ignored) {
            }
        }
        if (cityId == 0 && attraction != null) {
            cityId = attraction.getCityId();
        }
        boolean success = attractionService.deleteAttraction(id, user.getId());
        session.setAttribute("message", success ? "景点删除成功" : "景点删除失败，请重试");
        String redirectUrl;
        if (cityId > 0) {
            redirectUrl = req.getContextPath() + "/attraction/list?cityId=" + cityId;
        } else {
            redirectUrl = req.getContextPath() + "/city/list";
        }
        resp.sendRedirect(redirectUrl);
    }
}
