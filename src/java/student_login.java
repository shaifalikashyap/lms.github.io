import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class student_login extends HttpServlet {

   public void doGet(HttpServletRequest rq , HttpServletResponse rs){
       try{
           String a=rq.getParameter("t1");
           String b=rq.getParameter("t2");
           
           rs.setContentType("text/html");
           PrintWriter out=rs.getWriter();
           
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReConnect=true&useSSL=false", "root" , "root");
           Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select * from student where student_id= '"+a+"' and password = '"+b+"' ");
            
            if(rst.next())
            {
                rs.sendRedirect("after_student_login.html");
           }
            else{
                out.println("Wrong username or password");
            }
         
           
           
         
       }
       catch(Exception e)
       {
           
       }
   }
       
}