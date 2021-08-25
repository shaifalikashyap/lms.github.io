import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class update_issue_book extends HttpServlet {

   public void doGet(HttpServletRequest rq, HttpServletResponse rs)
   {
       
       try
       {
           //----------------------------------
           rs.setContentType("text/html");
           String id=rq.getParameter("t1");
           String id2=rq.getParameter("t2");
           String date=rq.getParameter("t3");
           
           PrintWriter out=rs.getWriter();
           //------------------------------------
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReconnect=true&useSSL=false","root","root");
           PreparedStatement ps=con.prepareStatement("update issue_book set  student_id=? ,book_id=?, date_of_issue=? ");
           ps.setString(1,id);
           ps.setString(2,id2);
           ps.setString(3,date);
           int row=ps.executeUpdate();
           if(row>0)
           {
                out.print("<html><marquee><h1>BOOK HAS BEEN ISSUED</h1></marquee></html>");
                rq.getRequestDispatcher("index.html").include(rq,rs);
           }
           else
           {
               out.print("Record not exist....Plz try again");
               rq.getRequestDispatcher("update_issue_book.html").include(rq,rs);
               
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
