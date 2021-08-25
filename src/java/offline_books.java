import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class offline_books extends HttpServlet {

   public void doGet(HttpServletRequest rq, HttpServletResponse rs)
   {
       
       try
       {
           //----------------------------------
           rs.setContentType("text/html");
           String user=rq.getParameter("t1");
           PrintWriter out=rs.getWriter();
           //------------------------------------
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReconnect=true&useSSL=false","root","root");
           PreparedStatement ps=con.prepareStatement("select * from books_lists where DEPARTMENT_NAME=?");
           ps.setString(1,user);
           ResultSet rs1=ps.executeQuery();
           out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>BOOK_ID</th><th>DEPARTMENT_NAME</th><th>BOOK_NAME</th><th>BOOK_AUTHOR</th><th>BOOK_EDITION</th><th>QUANTITY</th><tr>");
           while(rs1.next())    
                out.print("<tr><td>" + rs1.getString("BOOK_ID")+"</td><td>"+rs1.getString("DEPARTMENT_NAME")+"</td><td>"+rs1.getString("BOOK_NAME")+"</td><td>"+rs1.getString("BOOK_AUTHOR")+"</td><td>"+rs1.getString("BOOK_EDITION")+"</td><td>"+rs1.getString("QUANTITY")+"</td></tr>");
  
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