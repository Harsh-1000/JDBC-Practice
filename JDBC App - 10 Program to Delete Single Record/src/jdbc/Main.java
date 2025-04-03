package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Program to delete single record
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();

            String deleteQuery = "delete from employee where e_name='l'";

            int deletedRows = st.executeUpdate(deleteQuery);

            System.out.println("query executed successfully");
            System.out.println("no. of rows deleted = " + deletedRows);
        }
        catch (SQLException ex)
        {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }
    }
}
