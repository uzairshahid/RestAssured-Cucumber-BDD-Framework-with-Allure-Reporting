package Utilities.SQLJDBC;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresUtil {
    public static Postgres_connection postgres_connection = new Postgres_connection();

    public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
        //Create Statement Object
        Statement stmt = connection.createStatement();
        // Execute the SQL Query. Store results in ResultSet
        ResultSet resultSet = stmt.executeQuery(query);
        return resultSet;
    }

    public static List<String> getPOID(String DB, String query) throws SQLException, IOException {
        List<String> POIDList = new ArrayList<>();
        ResultSet resultSet;

        postgres_connection.setConnection(DB);
        System.out.println("Query to Execute: " + query);

        do {
            resultSet = PostgresUtil.getResultSet(postgres_connection.getConnection(), query);
        } while (resultSet == null);

        postgres_connection.quitConnection(postgres_connection.getConnection());

        while (resultSet.next()) {
            POIDList.add(resultSet.getString(1));
            System.out.println("Result Set: " + resultSet.getString(1));
        }

        return POIDList;
    }

    public static int updateRows(Connection connection, String query) throws SQLException {
        //Create Statement Object
        Statement stmt = connection.createStatement();
        // Execute the Update SQL Query. Store results in ResultSet
        int resultSet = stmt.executeUpdate(query);
        return resultSet;
    }


    public static int insertRows(Connection connection, String query) throws SQLException {
        //Create Statement Object
        Statement stmt = connection.createStatement();
        // Execute the Update SQL Query. Store results in ResultSet
        int resultSet = stmt.executeUpdate(query);
        return resultSet;
    }



    public static List<Map<String, Object>> selectRows(Connection connection, String query) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                rows.add(row);
            }
        }
        return rows;
    }
}
