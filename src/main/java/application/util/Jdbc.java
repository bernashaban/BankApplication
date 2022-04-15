package application.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc {

    public void run() throws IOException, ClassNotFoundException, SQLException {
        Properties props = new Properties();
        String dbConfigPath = Jdbc.class.getClassLoader()
                .getResource("jdbc.properties").getPath();
        props.load(new FileInputStream(dbConfigPath));
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver class not found");
            throw e;
        }
        System.out.println("DB driver loaded successfully");
        try (var con = DriverManager.getConnection(props.getProperty("url"), props)) {
        } catch (SQLException e) {
            System.out.println("Error creating connection to DB");
            throw e;
        }

    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        var demo = new Jdbc();
        demo.run();
    }
}

