package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PKListViewController implements Initializable {

    @FXML TableView<PrivateKeyModel> tableView;
    @FXML TableColumn coin;
    @FXML TableColumn privateKey;
    @FXML Button newKeyBtn;

    private ObservableList<PrivateKeyModel> privateKeyModelList;
    private WriterReader writerReader;
    private TableUtils tableUtils;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        writerReader = new WriterReader();
        tableUtils = new TableUtils();

        privateKeyModelList = FXCollections.observableArrayList(writerReader.ReadFromFile());
        if(!privateKeyModelList.isEmpty())
            showTable();
        else
            System.out.println("List is empty");
    }

    @FXML
    public void newKey(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/NewKey.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    private void showTable() {
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        coin.setSortable(true);
        coin.setSortType(TableColumn.SortType.ASCENDING);

        coin.setCellValueFactory(new PropertyValueFactory<PrivateKeyModel, String>("coin"));
        privateKey.setCellValueFactory(new PropertyValueFactory<PrivateKeyModel, String>("privateKey"));

        tableView.setItems(privateKeyModelList);


        tableView.setRowFactory( tv -> {
            TableRow<PrivateKeyModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    PrivateKeyModel rowData = row.getItem();
                    // create clipboard content.txt
                    final ClipboardContent clipboardContent = new ClipboardContent();
                    clipboardContent.putString(rowData.getPrivateKey());
                    Clipboard.getSystemClipboard().setContent(clipboardContent);
                }
            });
            return row ;
        });
        tableUtils.installCopyPasteHandler(tableView);
    }

    public void signOut(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/LoginView.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
