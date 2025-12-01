package org.x.travel.servlet;

import java.io.IOException;
import java.util.List;

import org.x.travel.entity.City;
import org.x.travel.entity.User;
import org.x.travel.service.CityService;
import org.x.travel.service.impl.CityServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/city/list")
public class CityListServlet extends HttpServlet {
    private final CityService cityService = new CityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        List<City> cities = cityService.listCities();
        req.setAttribute("cities", cities);
        req.getRequestDispatcher("/city_list.jsp").forward(req, resp);
    }
}
