package org.x.travel.servlet;

import java.io.IOException;

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

@WebServlet("/city/add")
public class CityAddServlet extends HttpServlet {
    private final CityService cityService = new CityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/city_form.jsp").forward(req, resp);
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
        String name = req.getParameter("name");
        String province = req.getParameter("province");
        City city = new City();
        city.setName(name);
        city.setProvince(province);
        boolean success = cityService.createCity(city, user.getId());
        session.setAttribute("message", success ? "城市新建成功" : "城市新建失败，请重试");
        resp.sendRedirect(req.getContextPath() + "/city/list");
    }
}
