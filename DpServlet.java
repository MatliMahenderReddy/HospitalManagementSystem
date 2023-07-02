import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/dps")
public class DpServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=(int)Long.parseLong(req.getParameter("id"));
		int bill=(int)Long.parseLong(req.getParameter("bill"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			PreparedStatement ps=con.prepareStatement("select * from hsp where id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			int bills=0;
			int days=0;
			while(rs.next())
			{
				 bills=rs.getInt(6);
				 days=rs.getInt(5);
			}
			bills=bills*days;
			if(bills==bill)
			{
				PreparedStatement pss=con.prepareStatement("delete from hsp where id=?");
				pss.setInt(1,id);
			    pss.executeUpdate();
			    
			    RequestDispatcher rq = req.getRequestDispatcher("index.html");
				rq.forward(req, resp);
			}
			else {
				PrintWriter pw=resp.getWriter();
		    	pw.println("your billamount:  "+bill);
		    	pw.println("total amount: "+bills);
		    	int amount=bills-bill;
		    	pw.println("remaining  your amount:    "+amount);
		    	
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
