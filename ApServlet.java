import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Adp")
public class ApServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=(int)Long.parseLong(req.getParameter("id"));
		String name=req.getParameter("name");
		String dicese=req.getParameter("dicese");
		String place=req.getParameter("place");
		int days=(int)Long.parseLong(req.getParameter("days"));
		int bill=(int)Long.parseLong(req.getParameter("bill"));
		bill=bill*days;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			PreparedStatement ps=con.prepareStatement("insert into hsp values(?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, dicese);
			ps.setString(4, place);
			ps.setInt(5, days);
			ps.setInt(6, bill);
			ps.executeUpdate();
	    	PrintWriter pw=resp.getWriter();
	    	pw.print("Successfully insert");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher rd=req.getRequestDispatcher("index.html");
		rd.forward(req, resp);
	}

}
