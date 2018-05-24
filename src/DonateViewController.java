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

public class DonateViewController implements Initializable {

    @FXML TextArea donationTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        donationTextArea.appendText("If you like this application and can afford to miss some altcoins,\n" +
                "feel free to donate. I have created this application for free,\n" +
                "and it is free to use for everyone. \n" +
                "I will try to do more for the Crypto Community.\n" +
                "If you have any feedback or new ideas feel free to mail me at\n" +
                "hugocryptos@gmail.com\n\n" +
                "Bitcoin\n" +
                "3N7NN5WG7zb6s18vNEaD2Z2eEKPbJpxtJL\n" +
                "\n" +
                "Litecoin\n" +
                "LUNgdhjCE16igxrLo5gyUPtrVYNo6tUmAg\n" +
                "\n" +
                "Ethereum or ERC-20 tokens\n" +
                "0x6337d9898Faac95E3538F6a33e445C052A90b795\n" +
                "\n" +
                "Doge\n" +
                "DFBs45x66UC2ghrrf9Kk8wDC7HfkGrgk4K\n" +
                "\n" +
                "Xrp\n" +
                "rBWZMEL9bZT6L9P1VBaKmJRCvkcMckAK1D\n" +
                "\nThank you very much for your support!");
    }

    public void back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        Scene scene = new Scene(root,1024,700);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
