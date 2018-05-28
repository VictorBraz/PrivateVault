package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    @FXML private TextField passwordTextfield;
    @FXML private Label emptyPasswdAlert;
    @FXML private Label passwdIncorrectAlert;
    @FXML private Label newAccLabel;


    @FXML
    public void login(ActionEvent event) throws Exception{
        emptyPasswdAlert.setVisible(false);
        passwdIncorrectAlert.setVisible(false);
        newAccLabel.setVisible(false);

        if(passwordTextfield.getText().isEmpty()){
            emptyPasswdAlert.setVisible(true);
        }else {
            //File file = new File("./content.txt");
            //Scanner scanner = new Scanner(file);
            Preferences prefs = Preferences.userNodeForPackage(getClass());
            String hashed = (String) PrefObj.getObject(prefs, "Password");

            if(hashed != null) {
                Boolean authenticated = BCrypt.checkpw(passwordTextfield.getText(), hashed);

                if (authenticated){
                    Parent root = FXMLLoader.load(getClass().getResource("/main/resources/PKListView.fxml"));
                    Scene scene = new Scene(root,1024,700);

                    Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);

                }else{
                    emptyPasswdAlert.setVisible(false);
                    passwdIncorrectAlert.setVisible(true);
                    passwordTextfield.clear();
                }
            } else {
                newAccLabel.setVisible(true);
            }
        }
    }

    @FXML
    public void CreateAccount(ActionEvent event) throws Exception{

        newAccLabel.setVisible(false);
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/Disclaimer.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Donate(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/DonateView.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
