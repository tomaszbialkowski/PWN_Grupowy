package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Main(){
        System.out.println("Konstruktor klasy Main");
    }
    @Override
    public void init(){
        System.out.println("metoda init() klasy main");
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml")); //TU MICHALA WIDOK
        primaryStage.setTitle("LOGOWANIE");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Override
    public void stop(){
        System.out.println("metoda stop() klasy main");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
