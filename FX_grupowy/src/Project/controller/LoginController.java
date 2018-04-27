package Project.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Project.database.DataBaseConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    DataBaseConnect dbc;
    Connection conn;
//    int licznikLogowan = 3;

    private void confirmAction() {
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM admin WHERE login = ? and (paswd = ? or paswd = ?)");
            prep.setString(1, tf_login.getText());
            prep.setString(2, pf_pass.getText());
            prep.setString(3, tf_pass.getText());
            ResultSet wynikLogowania = prep.executeQuery();
            if (wynikLogowania.next()) {
//                if (!wynikLogowania.getString("login").equals("")) {
                    System.out.println("\n=========================================================");
                    System.out.println("===|||Zalogowano poprawnie do aplikacji JAKO ADMIN!|||===");
                    System.out.println("=========================================================\n");
                    try {
                        Stage administratorStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("../view/adminMainView.fxml")); //w tym miejscu wywołamy okno ADMINA po zalogowaniu!!!
                        administratorStage.setTitle("Witamy w panelu administratora!!!");
                        administratorStage.setScene(new Scene(root));
                        administratorStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                }
            } else {
                System.out.println("\n=======================================================");
                System.out.println("\tUWAGA: Wprowadzono niepoprawny login lub hasło!");
                System.out.println("=======================================================\n");
//                licznikLogowan = licznikLogowan - 1;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ALERT: Nieprawidłowe dane logowania!");
                alert.setHeaderText("Niepoprawny login lub hasło!");
//                alert.setContentText("Pozostało: " + licznikLogowan + " prób logowania!");
                alert.showAndWait();
//                if (licznikLogowan == 0) {
//                    System.out.println("To była Twoja OSTATNIA próba zalogowania! Nie udało się! Zapraszamy jak się zastanowisz nad sobą!");
//                    System.exit(0);
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n#############################");
            System.out.println("###Błędne zapytanie do DB!###");
            System.out.println("##############################\n");
        }
        tf_login.clear();
        pf_pass.clear();
        tf_pass.clear();
    }
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField pf_pass;
    @FXML
    private TextField tf_pass;
    @FXML
    private CheckBox cb_showP;
    @FXML
    private Button btn_send;
    @FXML
    void loginAction(MouseEvent event) {
        System.out.println("\nKLIKNĄŁEŚ ---> 'Wyślij'");
        confirmAction();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void keyLoginAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            System.out.println("\nZatwierdziłeś ENTERem");
            confirmAction();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else{
            System.out.println("Nie zatwierdziłeś ENTERem!!!");
        }
    }
    @FXML
    void showPassword(MouseEvent event) {
        if (cb_showP.isSelected()) {
            tf_pass.setVisible(true);
            pf_pass.setVisible(false);
            tf_pass.setText(pf_pass.getText());
        } else {
            tf_pass.setVisible(false);
            pf_pass.setVisible(true);
            pf_pass.setText(tf_pass.getText());
        }
    }
    public void initialize(){
        dbc = new DataBaseConnect();
        dbc.driverRegistration();
        conn = dbc.setConnection();
    }
}