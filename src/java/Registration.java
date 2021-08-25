import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Registration extends HttpServlet {

   public void doGet(HttpServletRequest rq , HttpServletResponse rs){
       try{
           String a=rq.getParameter("t1");
           String b=rq.getParameter("t2");
           String c=rq.getParameter("t3");
           String d=rq.getParameter("t4");
           String e=rq.getParameter("t5");
           String f=rq.getParameter("t6");
           String g=rq.getParameter("t7");
           String h=rq.getParameter("t8");
           
           rs.setContentType("text/html");
           PrintWriter out=rs.getWriter();
           
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReConnect=true&useSSL=false", "root" , "root");
            PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
           ps.setString(1, a);
           ps.setString(2, b);
           ps.setString(3, c);
           ps.setString(4, d);
           ps.setString(5, e);
           ps.setString(6, f);
           ps.setString(7, g);
           ps.setString(8, h);
           
           int row=ps.executeUpdate();
           if (row>0){
           rq.getRequestDispatcher("after_student_login.html").include(rq, rs);}
           else
               out.print("<html><marquee><h1>Database Error</h1></marquee></html>");
           con.close();
         
       }
       catch(Exception e)
       {
           
       }
   }
       
}