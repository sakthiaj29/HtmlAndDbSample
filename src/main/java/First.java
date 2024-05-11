import com.mysql.cj.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/first")
public class First extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        resp.getWriter().println(name);
        System.out.printf(name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first_name=req.getParameter("first_name");
        String last_name=req.getParameter("last_name");
        String mobile_number=req.getParameter("mobile_number");
        String dob=req.getParameter("dob");

        try {
            DriverManager.registerDriver(new Driver());
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root");
            PreparedStatement p=c.prepareStatement("insert into user(first_name, last_name, mobile_number, dob) values(?,?,?,?)");
            p.setString(1,first_name);
            p.setString(2,last_name);
            p.setString(3,mobile_number);
            p.setString(4,dob);

            p.executeUpdate();
            c.close();
            p.close();
            System.out.print("success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
