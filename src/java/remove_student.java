import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class remove_student extends HttpServlet {

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
           PreparedStatement ps=con.prepareStatement("delete from student where student_id=?");
           ps.setString(1,user);
           int row=ps.executeUpdate();
           if(row>0)
           {
                out.print("<html><marquee><h1>STUDENT HAS BEEN REMOVED</h1></marquee></html>");
                rq.getRequestDispatcher("index.html").include(rq,rs);
           }
           else
           {
               out.print("<html><marquee><h1>Record not exist....Plz input right details</h1></marquee></html>");
               rq.getRequestDispatcher("remove_student.html").include(rq,rs);
               
           }
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
