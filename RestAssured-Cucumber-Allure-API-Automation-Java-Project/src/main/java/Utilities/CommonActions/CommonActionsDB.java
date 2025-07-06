package Utilities.CommonActions;

import Utilities.SQLJDBC.Postgres_connection;

import Utilities.SQLJDBC.PostgresUtil;
import Utilities.SQLJDBC.Postgres_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommonActionsDB {
    static Postgres_connection postgres_connection = new Postgres_connection();

    public static ArrayList<String> executeQueryAndVerify(String DB, String column, String query) throws SQLException {

        ArrayList<String> resultList = new ArrayList<>();
        postgres_connection.setConnection(DB);
        ResultSet resultSet = PostgresUtil.getResultSet(postgres_connection.getConnection(), query);
        postgres_connection.quitConnection(postgres_connection.getConnection());
        while (resultSet.next()) {
            resultList.add(resultSet.getString(1));
        }
        return resultList;
    }
    public static ArrayList<String> executeQueryAndVerify(String DB, String query) throws SQLException {

        ArrayList<String> resultList = new ArrayList<>();
        postgres_connection.setConnection(DB);
        ResultSet resultSet = PostgresUtil.getResultSet(postgres_connection.getConnection(), query);
        postgres_connection.quitConnection(postgres_connection.getConnection());
        while (resultSet.next()) {
            resultList.add(resultSet.getString(1));
        }
        return resultList;
    }

    public static ArrayList<Integer> executeQueryAndVerifyInt(String DB, String query) throws SQLException {

        ArrayList<Integer> resultList = new ArrayList<>();
        postgres_connection.setConnection(DB);
        ResultSet resultSet = PostgresUtil.getResultSet(postgres_connection.getConnection(), query);
        postgres_connection.quitConnection(postgres_connection.getConnection());
        while (resultSet.next()) {
            resultList.add(resultSet.getInt(1));
        }
        return resultList;
    }
    public static ArrayList<String> executeUpdateQuery(String DB, String query) throws SQLException {
        postgres_connection.setConnection(DB);
        PostgresUtil.updateRows(postgres_connection.getConnection(), query);
        System.out.println("query executed");
        postgres_connection.quitConnection(postgres_connection.getConnection());
        return null;
    }


    public static Connection executeInsertQuery(String DB, String query) throws SQLException {
        postgres_connection.setConnection(DB);
        PostgresUtil.insertRows(postgres_connection.getConnection(), query);
        System.out.println("query executed");
        return null;
    }
}
