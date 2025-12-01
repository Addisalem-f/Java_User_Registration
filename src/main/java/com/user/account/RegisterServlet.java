package com.user.account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            int rows = stmt.executeUpdate();

            out.println("<html><body>");
            if (rows > 0) {
                out.println("<h2>Registration Successful!</h2>");
                out.println("<a href='login.jsp'>Login Here</a>");
            } else {
                out.println("<h2>Registration Failed!</h2>");
                out.println("<a href='register.jsp'>Try Again</a>");
            }
            out.println("</body></html>");

        } catch (SQLIntegrityConstraintViolationException e) {
            out.println("<html><body>");
            out.println("<h2>Username already exists!</h2>");
            out.println("<a href='register.jsp'>Try Again</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Database error!</h2>");
            out.println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
