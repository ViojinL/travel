package org.x.travel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x.travel.util.DBUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet("/city/edit")
public class CityEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Map<String, Object> city = DBUtil.queryOne("SELECT * FROM city WHERE id = ?", id);
        req.setAttribute("city", city);
        req.getRequestDispatcher("/city_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String province = req.getParameter("province");
        DBUtil.update("UPDATE city SET name = ?, province = ? WHERE id = ?", name, province, id);
        resp.sendRedirect("list");
    }
}
