package Project.database;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
    Connection conn;
    public void driverRegistration(){
        String driverClassName = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driverClassName);
            System.out.println("\t---Sterownik zarejestrowany poprawnie!---");
        } catch (ClassNotFoundException e) {
            System.out.println("UWAGA: nie zarejestrowano klasy sterownika");
        }
    }

    public Connection setConnection(){
//        String dbName = "pwngrup";  //nazwa bazy danych
        String urlConn = "jdbc:mysql://localhost:3306/pwngrup";
        try{
            conn = DriverManager.getConnection(urlConn, "root", "T764ia2a!");
            System.out.println("\tPołączenie (---conn---)z bazą ustanowione!!!");
        }
        catch (SQLException e){
            System.out.println("\n\tAWARIA z bazą danych!!! \n\t\tCheck dys ałt...");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: problem z bazą danych!");
            alert.setHeaderText("Awaria z bazą danych!");
            alert.setContentText("Szczegóły: Sprawdź połączenie z bazą danych \ni uruchom aplikację ponownie!!!");
            alert.showAndWait();
            System.exit(0);
        }
        return conn;
    }
}
