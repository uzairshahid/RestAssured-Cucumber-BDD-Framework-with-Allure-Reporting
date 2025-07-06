package Utilities.SQLJDBC;

import Utilities.RestAssured.JSONReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Utilities.Drivers.DriverManager.environmentConfig;


public class Postgres_connection {
    protected String dbURL, userName, password;
    private Connection connection = null;
    public static final String testEnvironmentFilePath = "src/test/resources/TestData/Environment.json";

    public void setConnection(String DB) throws SQLException {
        try {
            String DB_URL_local = JSONReader.jsonEnvNodeReader(testEnvironmentFilePath, System.getProperty("env"), "DB_URL");
            String DB_NAME_local = JSONReader.jsonEnvNodeReader(testEnvironmentFilePath, System.getProperty("env"), "DB_USERNAME");
            String DB_PASS_local = JSONReader.jsonEnvNodeReader(testEnvironmentFilePath, System.getProperty("env"), "DB_PASSWORD");

            DriverManager.registerDriver(new org.postgresql.Driver());
            DriverManager.registerDriver(new org.postgresql.Driver());

            System.out.println("DB: " + DB);

            dbURL = DB_URL_local;
            System.out.println("dbURL: " + dbURL);

            userName = DB_NAME_local;
            System.out.println("userName: " + userName);

            password =DB_PASS_local;
            System.out.println("password: " + password);

            connection = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("DB connection established");
        }
        catch (IOException e)
        {
            throw new SQLException(e.getMessage());

        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void quitConnection(Connection connection) throws SQLException {
        connection.close();
        System.out.println("connection close");
    }
}
