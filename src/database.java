import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    /*Cette méthode teste la connexion avec notre base de données, elle affiche un message d'erreur lorsqu'on n'a pas réussi à se connecter ou quand il y a
    une erreur de driver */

    public Connection dbStatus() {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_media?autoReconnect=true&useSSL=false", "root", "");
        } catch (SQLException e) {
            System.out.println("Erreur connexion!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver!");
        }
        return conn;
    }

}
