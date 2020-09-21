package servlet;

import model.dao.CustomerDAOImpl;
import model.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

@WebServlet("/result.xml")
public class Result extends HttpServlet {

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        StringWriter writer = new StringWriter();
        try {
            if (customer == null || !customerDAO.isExist(customer.getLogin(), customer.getPassword())) {
                req.getSession().setAttribute("error", "Unauthorized user!");
                resp.sendRedirect(req.getContextPath());
                return;
            }
            customer = customerDAO.read(customer.getId());
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("UTF-8");
            marshaller.marshal(customer, writer);
            PrintWriter out = resp.getWriter();
            out.print(writer.toString());
        } catch (JAXBException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
