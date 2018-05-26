package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Victor Machado Braz.
 * Created on 20/05/2018.
 */
public class DisclaimerController implements Initializable {

    @FXML private TextArea disclaimerTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        disclaimerTextArea.appendText("Use this application at your OWN RISK!\n" +
                "Your data is stored within the application.\n" +
                "We do not store your data on a server or cloud.\n" +
                "Do not use only this application to store your data.\n" +
                "Always print your data on a piece of paper and store it on a save place.\n" +
                "By forgetting your password your data will be lost and not recoverable.\n" +
                "It is NOT possible to restore your PASSWORD!\n" +
                "We are NOT RESPONSIBLE for any loss of your data or faults that could lead to data loss.\n" +
                "By using this application you agree with these terms.");

    }

    @FXML
    private void handleDisagreeButton(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/LoginView.fxml"));
        Scene scene = new Scene(root,1024,700);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleAgreeButton(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/SingUp.fxml"));
        Scene scene = new Scene(root,1024,700);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

}
