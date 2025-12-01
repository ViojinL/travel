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

@WebServlet("/attraction/list")
public class AttractionListServlet extends HttpServlet {
    private final AttractionService attractionService = new AttractionServiceImpl();
    private final CityService cityService = new CityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityId = req.getParameter("cityId");
        int cityIdInt;
        try {
            cityIdInt = Integer.parseInt(cityId);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
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
        List<Attraction> attractions = attractionService.listByCity(cityIdInt);
        City city = cityService.getCity(cityIdInt);
        if (city == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("attractions", attractions);
        req.setAttribute("city", city);
        req.getRequestDispatcher("/attraction_list.jsp").forward(req, resp);
    }
}
