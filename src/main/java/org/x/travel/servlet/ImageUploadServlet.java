package org.x.travel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.x.travel.util.DBUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/attraction/upload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        req.getRequestDispatcher("/upload_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        DBUtil.update("UPDATE attraction SET image_path = ? WHERE id = ?", "uploads/" + fileName, id);

        resp.sendRedirect("detail?id=" + id);
    }
}
