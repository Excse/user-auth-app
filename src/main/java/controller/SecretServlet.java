package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/app/secret")
public class SecretServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("images/secret.png");
                OutputStream output = res.getOutputStream()) {
            if (input == null) {
                res.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            res.setContentType("image/png");
            input.transferTo(output);
        } catch (IOException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
