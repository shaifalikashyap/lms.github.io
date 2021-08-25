import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class student_details extends HttpServlet {

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
           PreparedStatement ps=con.prepareStatement("select student_id,stu_name,course,branch,year,semester,contact_no from student");
           ResultSet rs1=ps.executeQuery();
           out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>STUDENT_ID</th><th>STUDENT_NAME</th><th>COURSE</th><th>BRANCH</th><th>YEAR</th><th>SEMESTER</th><th>CONTACT NUMBER</th><tr>");
           while(rs1.next())    
                out.print("<tr><td>" + rs1.getString("student_id")+"</td><td>"+rs1.getString("stu_name")+"</td><td>"+rs1.getString("course")+"</td><td>"+rs1.getString("branch")+"</td><td>"+rs1.getString("year")+"</td><td>"+rs1.getString("semester")+"</td><td>"+rs1.getString("contact_no")+"</td></tr>");
  
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
