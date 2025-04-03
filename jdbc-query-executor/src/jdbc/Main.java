package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        // database credential from environment variables
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // establish connection with database
        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();
            Scanner sc = new Scanner(System.in);

            while(true)
            {
                System.out.println("To Exit type (exit)");
                System.out.println("Enter Query: ");

                String sqlQuery = sc.nextLine();

                // exit condition
                if(sqlQuery.equalsIgnoreCase("exit")) {
                    return; // exit the program if user type "exit"
                }

                // execute the sql query
                boolean isDQL = st.execute(sqlQuery);

                if(isDQL) {
                    ResultSet rs = st.getResultSet();
                    showResult(rs);
                }
                else {
                    int rowEffected = st.getUpdateCount();
                    System.out.println("Query Executed: No. of row effected " + rowEffected);
                }
            }

        }
        catch(SQLException ex)
        {
            // handle SQL exceptions and print error details
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }

    }

    /**
     * Displays the result set from a SELECT query.
     * @param rs The ResultSet object containing query results.
     * @throws SQLException if an SQL error occurs while processing the result set.
     */
    private static void showResult(ResultSet rs) throws SQLException {
        System.out.println("result");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println("column count : " + columnCount );

        while (rs.next())
        {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnValue = rs.getString(i);
                System.out.print(columnName + ": " + columnValue + " | ");
            }
            System.out.println();
        }

    }

}