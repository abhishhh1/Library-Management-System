import java.sql.*;

public class ConnectDB {
    public static Connection getConnection() throws Exception{
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "debian-sys-maint", "U2nKe6K6TfSL6U2N");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
}
