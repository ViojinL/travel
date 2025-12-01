package org.x.travel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.x.travel.util.DBUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet("/attraction/detail")
public class AttractionDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Map<String, Object> attraction = DBUtil.queryOne("SELECT * FROM attraction WHERE id = ?", id);
        req.setAttribute("attraction", attraction);
        req.getRequestDispatcher("/attraction_detail.jsp").forward(req, resp);
    }
}
