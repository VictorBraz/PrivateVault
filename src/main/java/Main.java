package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/LoginView.fxml"));
        this.primaryStage.setTitle("PrivateVault");
        this.primaryStage.setHeight(700);
        this.primaryStage.setWidth(1024);
        this.primaryStage.setScene(new Scene(root, 300, 275));
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
