package edu.ducky.btc.scenes;

import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.bitcoinj.core.Coin;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/edu/ducky/btc/scenes/mainWallet.fxml")
public class MainWalletController implements Initializable {

    @FXML
    private Label labelBTCBalance;

    @FXML
    private Button buttonSend;

    @FXML
    private Button buttonReceive;

    @FXML
    private FlowPane paneTransactions;

    @FXML
    void pressedReceive(ActionEvent event) {

    }

    @FXML
    void pressedSend(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelBTCBalance.setText(String.valueOf(WalletApplication.getWallet().getBalance().getValue()));

    }

    private Pane paneTransaction(){

        Pane pane = new Pane();
        pane.setPrefSize(500, 50);
        pane.setStyle("-fx-background-color: #000000;");

        Label dateLabel = new Label("Fecha:");
        dateLabel.setLayoutX(14);
        dateLabel.setLayoutY(6);
        pane.getChildren().add(dateLabel);

        Label date = new Label("Fecha");
        date.setLayoutX(54);
        date.setLayoutY(6);
        pane.getChildren().add(date);

        Label ammountLabel = new Label("Cantidad:");
        ammountLabel.setLayoutX(311);
        ammountLabel.setLayoutY(6);
        pane.getChildren().add(ammountLabel);

        Label ammount = new Label("Cantidad");
        ammount.setLayoutX(250);
        ammount.setLayoutY(6);
        pane.getChildren().add(ammount);

        Label addressLabel = new Label("Address:");
        addressLabel.setLayoutX(14);
        addressLabel.setLayoutY(28);
        pane.getChildren().add(addressLabel);

        Label address = new Label("Address");
        address.setLayoutX(68);
        address.setLayoutY(28);
        pane.getChildren().add(address);

        return pane;

    }
}
