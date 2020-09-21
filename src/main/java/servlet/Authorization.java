package servlet;

import model.dao.CustomerDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorization")
public class Authorization extends HttpServlet {

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("error");
        try {
            if (customerDAO.isExist(req.getParameter("login"), req.getParameter("password"))) {
                req.getSession().setAttribute("customer", customerDAO.getByLoginPassword(req.getParameter("login"), req.getParameter("password")));
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }
            req.getSession().setAttribute("error", "Wrong login or password!");
            resp.sendRedirect(req.getContextPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
