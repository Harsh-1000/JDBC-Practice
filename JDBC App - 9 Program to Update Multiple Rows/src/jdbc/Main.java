package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Program updates all employee salaries by adding a 60% bonus.
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();

            // updates all employee salaries by adding a 60% bonus.
            String updateQuery = "update employee set e_salary = e_salary + (e_salary*0.6)";

            int updatedRows = st.executeUpdate(updateQuery);

            System.out.println("query executed successfully");
            System.out.println("no.of rows updated  = " + updatedRows);
        }
        catch (SQLException ex)
        {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }
    }
}
