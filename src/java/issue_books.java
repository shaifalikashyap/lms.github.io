import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class issue_books extends HttpServlet {

   public void doGet(HttpServletRequest rq , HttpServletResponse rs){
       try{
           String a=rq.getParameter("t1");
           String b=rq.getParameter("t2");
           String c=rq.getParameter("t3");
           String d=rq.getParameter("t4");
           String e=rq.getParameter("t5");
           
           rs.setContentType("text/html");
           PrintWriter out=rs.getWriter();
           
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReConnect=true&useSSL=false", "root" , "root");
            PreparedStatement ps=con.prepareStatement("insert into issue_book values(?,?,?,?,?)");
           ps.setString(1, a);
           ps.setString(2, b);
           ps.setString(3, c);
           ps.setString(4, d);
            ps.setString(5, e);
           
           
           int row=ps.executeUpdate();
           if (row>0){
               out.print("<html><marquee><h1>YOUR BOOK HAS BEEN ISSUED</h1></marquee></html>");
           rq.getRequestDispatcher("index.html").include(rq, rs);}
           else
               out.print("<html><marquee><h1>Database Error</h1></marquee></html>");
           con.close();
         
       }
       catch(Exception e)
       {
           
       }
   }
       
}