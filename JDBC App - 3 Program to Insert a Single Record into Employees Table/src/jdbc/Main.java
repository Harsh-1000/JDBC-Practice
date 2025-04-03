package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Insert a Single row in employee table
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();

            String insertRow = "insert into employee (e_name,e_salary,e_address) values('Rohit Sharma',55000,'Mumbai, India')";

            int effectedRows = st.executeUpdate(insertRow);

            System.out.println("Numbers of row inserted: " + effectedRows);
        }
        catch (SQLException ex)
        {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }

    }
}
