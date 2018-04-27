package Project.controller;

import Project.database.DataBaseConnect;
import Project.model.ProjektModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.sql.*;

public class AdminController {
    DataBaseConnect db;
    Connection conn;
    
    @FXML
    private TableColumn<ProjektModel, Integer> c_id_user;
    @FXML
    private TableColumn<ProjektModel, String> c_name;
    @FXML
    private TableColumn<ProjektModel, String> c_last;
    @FXML
    private TableColumn<ProjektModel, String> c_p1;
    @FXML
    private TableColumn<ProjektModel, String> c_p2;
    @FXML
    private TableColumn<ProjektModel, String> c_p3;
    @FXML
    private TableColumn<ProjektModel, String> c_p4;
    @FXML
    private TextField tf_p1_grade;
    @FXML
    private TextField tf_p2_grade;
    @FXML
    private TextField tf_p3_grade;
    @FXML
    private TextField tf_p4_grade;
    @FXML
    private TextField tf_p1_desc;
    @FXML
    private TextField tf_p2_desc;
    @FXML
    private TextField tf_p3_desc;
    @FXML
    private TextField tf_p4_desc;
    @FXML
    private Button b_view;
    @FXML
    private Button b_select;
    @FXML
    private GridPane gp;
    @FXML
    void selectStu(MouseEvent event) {
        gp.setDisable(false);
        b_addGrade.setDisable(false);
        b_select.setDisable(false);
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("WARNING: Wskaż konkretnego kursanta!");
//        alert.setHeaderText("Wskaż konkretnego kursanta!");
//        alert.setContentText("Szczegóły: Wskaż konkretnego kursanta!!!");
//        alert.showAndWait();
    }
    @FXML
    private Button b_addGrade;
    @FXML
    private TableView<ProjektModel> tab_all;

    private void externalCancel(){
        gp.setDisable(true);
        b_addGrade.setDisable(true);
        b_select.setDisable(true);
        tf_p1_grade.clear();
        tf_p2_grade.clear();
        tf_p3_grade.clear();
        tf_p4_grade.clear();
        tf_p1_desc.clear();
        tf_p2_desc.clear();
        tf_p3_desc.clear();
        tf_p4_desc.clear();
    }

    @FXML
    void addGrade(MouseEvent event) {
        int id_selected_to_addGrade = tab_all.getSelectionModel().getSelectedItem().getId_user();
        try {
            if(tf_p1_grade.getText().equals("") || tf_p1_desc.getText().equals("") || tf_p2_grade.getText().equals("") || tf_p2_desc.getText().equals("") || tf_p3_grade.getText().equals("") || tf_p3_desc.getText().equals("") || tf_p4_grade.getText().equals("") || tf_p4_desc.getText().equals("")){
                System.out.println("\n###############################################");
                System.out.println("----------Wypełnij wszystkie pola--------------");
                System.out.println("###############################################\n");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Uzupełnij wszystkie pola");
                alert.setHeaderText("Uzupełnij wszystkie pola!");
                alert.setContentText("Szczegóły: Uzupełnij wszystkie pola!!!");
                alert.showAndWait();
            }
            else{
//                PreparedStatement prep = conn.prepareStatement("UPDATE projekt SET p1=?, o1=? WHERE id_user = ?");
                PreparedStatement prep = conn.prepareStatement("UPDATE kursant SET p1=?, p2=?, p3=?, p4=?, o1=?, o2=?, o3=?, o4=? WHERE user_id = ?");
                prep.setString(1, tf_p1_grade.getText());
                prep.setString(2, tf_p2_grade.getText());
                prep.setString(3, tf_p3_grade.getText());
                prep.setString(4, tf_p4_grade.getText());
                prep.setString(5, tf_p1_desc.getText());
                prep.setString(6, tf_p2_desc.getText());
                prep.setString(7, tf_p3_desc.getText());
                prep.setString(8, tf_p4_desc.getText());
                prep.setInt(9, id_selected_to_addGrade);
                prep.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Oceny zostały dodane");
                alert.setHeaderText("Oceny dodane!");
                alert.setContentText("Szczegóły: Oceny z kursów zostały wprowadzone poprawnie!!!");
                alert.showAndWait();
                System.out.println("\n====================================================");
                System.out.println("==Admin zaktualizował oceny dla uczestnika kursu!!==");
                System.out.println("====================================================\n");
                externalCancel();
                externalSelect();
                b_select.setDisable(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<ProjektModel> listaZgloszen = FXCollections.observableArrayList();
    private void externalSelect(){
        listaZgloszen.clear();
        try {
            listaZgloszen.clear();
            Statement c = conn.createStatement();
            ResultSet zgloszenia = c.executeQuery("SELECT user_id,imie,nazwisko,p1,p2,p3,p4,o1,o2,o3,o4 FROM kursant"); //wypisac interesujace klumny
            while(zgloszenia.next()) {
                ProjektModel pm = new ProjektModel(
                        zgloszenia.getInt("user_id"),
                        zgloszenia.getString("imie"),
                        zgloszenia.getString("nazwisko"),
                        zgloszenia.getString("p1"),
                        zgloszenia.getString("p2"),
                        zgloszenia.getString("p3"),
                        zgloszenia.getString("p4"),
                        zgloszenia.getString("o1"),
                        zgloszenia.getString("o2"),
                        zgloszenia.getString("o3"),
                        zgloszenia.getString("o4")
                );
                listaZgloszen.add(pm);
            }
            c_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            c_name.setCellValueFactory(new PropertyValueFactory<>("imie"));
            c_last.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            c_p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
            c_p2.setCellValueFactory(new PropertyValueFactory<>("p2"));
            c_p3.setCellValueFactory(new PropertyValueFactory<>("p3"));
            c_p4.setCellValueFactory(new PropertyValueFactory<>("p4"));
            tab_all.setItems(listaZgloszen);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectAction(MouseEvent event) {
        externalSelect();
        b_select.setDisable(false);
    }

    public void initialize(){
        db = new DataBaseConnect();
        db.driverRegistration();
        conn = db.setConnection();
    }
}
