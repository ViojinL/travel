package org.x.travel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x.travel.util.DBUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/city/list")
public class CityListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, Object>> cities = DBUtil.query("SELECT * FROM city");
        req.setAttribute("cities", cities);
        req.getRequestDispatcher("/city_list.jsp").forward(req, resp);
    }
}
