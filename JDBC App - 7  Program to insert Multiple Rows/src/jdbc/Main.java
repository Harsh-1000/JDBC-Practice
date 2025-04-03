package jdbc;

import java.sql.*;
import java.util.Scanner;

/**
 * Program to insert multiple rows
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

            while(true)
            {
                System.out.println("Enter employee name : ");
                String e_name = sc.nextLine();
                System.out.println("Enter employee salary : ");
                double e_salary = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter employee address : ");
                String e_address = sc.nextLine();

                String insertRow = "insert into employee (e_name, e_salary, e_address) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = con.prepareStatement(insertRow);

                preparedStatement.setString(1,e_name);
                preparedStatement.setDouble(2,e_salary);
                preparedStatement.setString(3,e_address);

                int affectedRows = preparedStatement.executeUpdate();

                System.out.println("Numbers of row inserted: " + affectedRows);

                System.out.println("Insert more row: (yes/no) ");
                String choice = sc.next();
                sc.nextLine();
                if(choice.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }
        catch (SQLException ex)
        {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }
    }
}
