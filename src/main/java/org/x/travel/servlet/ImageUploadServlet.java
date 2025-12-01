package org.x.travel.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.x.travel.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/attraction/upload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        Map<String, Object> attraction = DBUtil.queryOne("SELECT city_id FROM attraction WHERE id = ?", id);
        if (attraction != null) {
            req.setAttribute("cityId", attraction.get("city_id"));
        }
        req.getRequestDispatcher("/upload_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Ensure upload directory exists
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        // Save file
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Update DB
        int rows = DBUtil.update("UPDATE attraction SET image_path = ? WHERE id = ?", "uploads/" + fileName, id);
        HttpSession session = req.getSession();
        session.setAttribute("message", rows > 0 ? "图片上传成功" : "图片上传失败，请重试");
        resp.sendRedirect(req.getContextPath() + "/attraction/detail?id=" + id);
    }
}
