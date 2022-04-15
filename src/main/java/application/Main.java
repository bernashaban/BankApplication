package application;

import application.util.Jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static application.util.JdbcUtils.closeConnection;
import static application.util.JdbcUtils.createDbConnection;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        String dbConfigPath = Jdbc.class.getClassLoader().getResource("jdbc.properties").getPath();
        try {
            props.load(new FileInputStream(dbConfigPath));
        } catch (IOException e) {
            System.out.println("Error loading files.");
        }
        Connection conn = null;
        try {
            conn = createDbConnection(props);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creating connection with the database.");
        }

        try {
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error while closing the connection with database.");
        }
    }
}
