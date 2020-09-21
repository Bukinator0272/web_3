package servlet;

import model.dao.CustomerDAOImpl;
import model.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/home")
public class Home extends HttpServlet {

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        try {
            if (customer == null || !customerDAO.isExist(customer.getLogin(), customer.getPassword())) {
                req.getSession().setAttribute("error", "Unauthorized user!");
                resp.sendRedirect(req.getContextPath());
                return;
            }
            customer = customerDAO.read(customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("customerData", customer);
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
    }

}
