package org.x.travel.servlet;

import java.io.IOException;
import java.math.BigDecimal;

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

@WebServlet("/attraction/edit")
public class AttractionEditServlet extends HttpServlet {
    private final AttractionService attractionService = new AttractionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Attraction attraction = attractionService.getDetail(id);
        if (attraction == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("attraction", attraction);
        req.setAttribute("cityId", attraction.getCityId());
        req.getRequestDispatcher("/attraction_form.jsp").forward(req, resp);
    }

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
        String cityIdParam = req.getParameter("cityId");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");
        int id;
        int cityId;
        try {
            id = Integer.parseInt(idParam);
            cityId = Integer.parseInt(cityIdParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Attraction attraction = new Attraction();
        attraction.setId(id);
        attraction.setCityId(cityId);
        attraction.setName(name);
        attraction.setPrice(price);
        attraction.setDescription(description);
        boolean success = attractionService.updateAttraction(attraction, user.getId());
        session.setAttribute("message", success ? "景点编辑成功" : "景点编辑失败，请重试");
        resp.sendRedirect(req.getContextPath() + "/attraction/list?cityId=" + cityId);
    }
}
