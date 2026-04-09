package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.Properties;

public class DBConnection {

    private static final Properties envProps = new Properties();

    static {
        loadEnv();
    }

    public static void loadEnv() {
        String[] candidates = {
                ".env", "../.env"
        };

        for (String path : candidates) {
            try (InputStream in = new FileInputStream(path)) {
                envProps.load(in);

            } catch (Exception ignored) {

            }
        }
    }

    private static String getValue(String key) {
        String v = envProps.getProperty(key);
        if (v != null && !v.trim().isEmpty())
            return v.trim();

        // fallback to OS env var if present
        v = System.getenv(key);
        if (v != null && !v.trim().isEmpty())
            return v.trim();

        return null;
    }

    public static Connection getConnection() throws SQLException {
        String host = getValue("MYSQLHOST");
        String port = getValue("MYSQLPORT");
        String db = getValue("MYSQLDATABASE");
        String user = getValue("MYSQLUSER");
        String pass = getValue("MYSQLPASSWORD");

        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        if (port == null || port.isEmpty()) {
            port = "3306";
        }
        if (db == null || user == null) {
            throw new SQLException(
                    "Missing DB config in .env (MYSQLDATABASE, MYSQLUSER and optionally MYSQLHOST, MYSQLPORT, MYSQLPASSWORD).");
        }
        if (pass == null) {
            pass = "";
        }

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found. Add mysql-connector-j jar to classpath.", e);
        }

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException firstException) {
            if (!"3306".equals(port)) {
                String fallbackUrl = "jdbc:mysql://" + host + ":3306/" + db
                        + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                try {
                    return DriverManager.getConnection(fallbackUrl, user, pass);
                } catch (SQLException ignored) {
                }
            }

            throw new SQLException(
                    "Unable to connect to MySQL at " + host + ":" + port + "/" + db
                            + ". Check that the MySQL server is running and that the .env values are correct. If you are using local MySQL, port 3306 is usually the correct port.",
                    firstException);
        }
    }
}
