import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    public static Connection getConnection() {
        try {
            String dbURL = "@gameproject-mysql.cltwlujwqx82.us-east-1.rds.amazonaws.com";
            String dbID = "root";
            String dbPW = "12341234";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbURL, dbID, dbPW);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}