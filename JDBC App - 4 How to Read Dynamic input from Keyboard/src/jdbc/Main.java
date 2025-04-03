package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * To read dynamic input from keyboard and insert row
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter employee name : ");
            String e_name = sc.nextLine();
            System.out.println("Enter employee salary : ");
            double e_salary = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter employee address : ");
            String e_address = sc.nextLine();

            String insertRow = "INSERT INTO employee (e_name, e_salary, e_address) VALUES ('"
                    + e_name + "', "
                    + e_salary + ", '"
                    + e_address + "')";

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
