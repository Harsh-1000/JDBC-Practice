package jdbc;

import java.sql.*;

/**
 * Program to select all rows from a table
 */
public class Main {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(Connection con = DriverManager.getConnection(url,user,password))
        {
            Statement st = con.createStatement();

            String selectQuery = "select * from employee";

            ResultSet rs = st.executeQuery(selectQuery);

            showResult(rs);
        }
        catch (SQLException ex)
        {
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

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next())
        {
            for(int i=1;i<=columnCount;i++)
            {
                String columnName = metaData.getColumnName(i);
                String columnValue = rs.getString(i);
                System.out.print(columnName + ": " + columnValue + " | ");
            }
            System.out.println();
        }

    }
}
