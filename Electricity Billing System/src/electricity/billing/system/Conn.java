package electricity.billing.system;
import java.sql.*;
public class Conn {
    Connection c;
    Statement st;
    Conn()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost/electricitybilling","root","");  
            st=c.createStatement();
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
    }
}
