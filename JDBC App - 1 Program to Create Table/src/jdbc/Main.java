package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This program connects to a PostgresSQL database and creates an "employee" table.
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password)) {

            Statement st = con.createStatement();

            String createTable = "create table employee (id SERIAL PRIMARY KEY, e_name varchar(255), e_salary numeric(10,2),e_address varchar(255))";

            st.executeUpdate(createTable);

            System.out.println("Table created successfully");

        }
        catch (SQLException ex)
        {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }
    }
}
