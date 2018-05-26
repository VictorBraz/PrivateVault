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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewKeyController implements Initializable {

    @FXML TextField coinNameField;
    @FXML TextField privateKeyfield;
    @FXML Label emptyFields;

    private PrivateKeyModel privateKeyModel;
    private WriterReader writerReader;
    private ArrayList<PrivateKeyModel> pkList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addNewKey(ActionEvent event) throws Exception{

        if(!coinNameField.getText().isEmpty() && !privateKeyfield.getText().isEmpty()){
            pkList = new ArrayList<>();
            emptyFields.setVisible(false);

            privateKeyModel = new PrivateKeyModel();
            writerReader = new WriterReader();

            privateKeyModel.setCoin(coinNameField.getText());
            privateKeyModel.setPrivateKey(privateKeyfield.getText());

            pkList = writerReader.ReadFromFile();
            pkList.add(privateKeyModel);

            writerReader.WriteToFile(pkList);

            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/PKListView.fxml"));
            Scene scene = new Scene(root,1024,700);

            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);

        }else {
            emptyFields.setVisible(true);
        }
    }

    public void back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/PKListView.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }
}

