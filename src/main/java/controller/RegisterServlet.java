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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final UserDAOImpl USER_DAO = DAOFactory.getUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        if (USER_DAO.getUserByUsername(username) != null) {
            req.setAttribute("error", "Username already exists");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
            return;
        }

        if (USER_DAO.getUserByEmail(email) != null) {
            req.setAttribute("error", "Email already exists");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        if (!USER_DAO.createUser(username, hashedPassword, firstName, lastName, email)) {
            req.setAttribute("error", "Failed to create user");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
            return;
        }

        res.sendRedirect(req.getContextPath() + "/login");
    }

}
