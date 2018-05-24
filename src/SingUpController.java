import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SingUpController implements Initializable {
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField rePasswordField;
    @FXML private Label emptyFieldLabel;
    @FXML private Label noMatchLabel;

    private WriterReader writer;

    @FXML
    private void createAccount(ActionEvent event) throws Exception{
        emptyFieldLabel.setVisible(false);
        noMatchLabel.setVisible(false);
        if (!newPasswordField.getText().isEmpty() && !rePasswordField.getText().isEmpty()){
            if (newPasswordField.getText().equals(rePasswordField.getText())){

                writer = new WriterReader();

                String encrypted = BCrypt.hashpw(newPasswordField.getText(), BCrypt.gensalt());

                writer.WriteContent(encrypted);

                ArrayList<PrivateKeyModel> newList = new ArrayList<>();
                writer.WriteToFile(newList);

                Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
                Scene scene = new Scene(root,1024,700);

                Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
            }
            else{
                noMatchLabel.setVisible(true);
            }
        }else {
            emptyFieldLabel.setVisible(true);
        }
    }

    public void back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
