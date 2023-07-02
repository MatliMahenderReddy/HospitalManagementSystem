import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vsp")
public class ViewPatient extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
		
    	PreparedStatement ps=con.prepareStatement("select * from hsp");    	
    	ResultSet rs=ps.executeQuery();
    	PrintWriter pw=resp.getWriter();
    	
    	pw.println(" Patients Deatils ");
    	  
    	pw.print(" ID   "+ "         Name "+"        Dicese "+"         Place "+"       Days "+"     Bill ");
    	pw.println("");
		   while(rs.next())
		   {
			   
			   
			   
			   pw.print("  "+ rs.getInt(1)+" ");
			   pw.print("           "+rs.getString(2)+" ");
			   pw.print("       "+rs.getString(3)+" ");
			   pw.print("      "+rs.getString(4)+" ");
			   pw.print("      "+rs.getInt(5)+" ");
			   pw.print("        "+rs.getInt(6)+" ");
			   pw.println("                      ");
			 
		   }
    	
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
}
	}
	


