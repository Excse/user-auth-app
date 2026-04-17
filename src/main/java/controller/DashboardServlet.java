package controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", user.getLocale());
        req.setAttribute("created_at", user.getCreatedAt().toLocalDateTime().format(formatter));
        req.setAttribute("updated_at", user.getUpdatedAt().toLocalDateTime().format(formatter));
        req.setAttribute("last_accessed", user.getLastAccessed().toLocalDateTime().format(formatter));
        req.setAttribute("locale", user.getLocale().toLanguageTag());

        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
    }

}
