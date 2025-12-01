package org.x.travel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x.travel.util.DBUtil;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/attraction/add")
public class AttractionAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityId = req.getParameter("cityId");
        req.setAttribute("cityId", cityId);
        req.getRequestDispatcher("/attraction_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityId = req.getParameter("cityId");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");

        BigDecimal price = new BigDecimal(priceStr);

        DBUtil.update("INSERT INTO attraction (city_id, name, price, description) VALUES (?, ?, ?, ?)",
                cityId, name, price, description);

        resp.sendRedirect("list?cityId=" + cityId);
    }
}
