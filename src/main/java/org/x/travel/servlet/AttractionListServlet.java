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

@WebServlet("/attraction/list")
public class AttractionListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityId = req.getParameter("cityId");
        List<Map<String, Object>> attractions = DBUtil.query("SELECT * FROM attraction WHERE city_id = ?", cityId);
        Map<String, Object> city = DBUtil.queryOne("SELECT * FROM city WHERE id = ?", cityId);

        req.setAttribute("attractions", attractions);
        req.setAttribute("city", city);
        req.getRequestDispatcher("/attraction_list.jsp").forward(req, resp);
    }
}
