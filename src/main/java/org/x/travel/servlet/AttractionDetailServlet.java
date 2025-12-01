package org.x.travel.servlet;

import java.io.IOException;
import java.util.List;

import org.x.travel.entity.Attraction;
import org.x.travel.entity.City;
import org.x.travel.entity.User;
import org.x.travel.service.AttractionService;
import org.x.travel.service.CityService;
import org.x.travel.service.impl.AttractionServiceImpl;
import org.x.travel.service.impl.CityServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/attraction/detail")
public class AttractionDetailServlet extends HttpServlet {
    private final AttractionService attractionService = new AttractionServiceImpl();
    private final CityService cityService = new CityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int attractionId;
        try {
            attractionId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Attraction attraction = attractionService.getDetail(attractionId);
        if (attraction == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        City city = cityService.getCity(attraction.getCityId());
        int serial = 0;
        if (city != null) {
            List<Attraction> attractions = attractionService.listByCity(city.getId());
            for (int i = 0; i < attractions.size(); i++) {
                if (attractions.get(i).getId() == attraction.getId()) {
                    serial = i + 1;
                    break;
                }
            }
        }
        HttpSession session = req.getSession(false);
        if (session != null) {
            String message = (String) session.getAttribute("message");
            if (message != null) {
                req.setAttribute("message", message);
                session.removeAttribute("message");
            }
            User user = (User) session.getAttribute("user");
            if (user != null) {
                req.setAttribute("username", user.getUsername());
            }
        }
        req.setAttribute("attraction", attraction);
        req.setAttribute("cityName", city != null ? city.getName() : "");
        req.setAttribute("serial", serial);
        req.getRequestDispatcher("/attraction_detail.jsp").forward(req, resp);
    }
}
