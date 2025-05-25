import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
    public Connection connectDB() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&serverTimezone=UTC"; 
            String user = "root";
            String password = "123456"; 

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }

        return conn;
    }
}
