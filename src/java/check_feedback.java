import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class check_feedback extends HttpServlet {

   public void doGet(HttpServletRequest rq, HttpServletResponse rs)
   {
       
       try
       {
           //----------------------------------
           rs.setContentType("text/html");
           
           PrintWriter out=rs.getWriter();
           //------------------------------------
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReconnect=true&useSSL=false","root","root");
           PreparedStatement ps=con.prepareStatement("select * from feedback");
           ResultSet rs1=ps.executeQuery();
           out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>WOULD YOU LIKE TO RECOMMEND TO ADD ANY BOOK IN LIBRARY</th><th>YOU FACED ANY PROBLEM WHILE USING THIS WEBSITE</th><th>COLLECTION OF BOOKS IN LIBRARY ARE GOOD, AVERAGE OR BAD</th><<tr>");
           while(rs1.next())    
                out.print("<tr><td>" + rs1.getString("add")+"</td><td>"+rs1.getString("problem")+"</td><td>"+rs1.getString("collection")+"</td></tr>");
  
       }
       catch(Exception e)
       {
           try{
           PrintWriter out=rs.getWriter();
           out.print(e.getMessage());
           }
           catch(Exception ee)
           {
               
           }
       }
   }
}
