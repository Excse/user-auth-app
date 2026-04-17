package controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import dao.DAOFactory;
import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserDAOImpl USER_DAO = DAOFactory.getUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = USER_DAO.getUserByUsername(username);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
            return;
        }

        req.getSession().setAttribute("user", user);
        res.sendRedirect(req.getContextPath() + "/app/dashboard");
    }

}
