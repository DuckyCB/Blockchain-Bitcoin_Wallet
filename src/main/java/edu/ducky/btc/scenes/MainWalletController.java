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
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/edu/ducky/btc/scenes/mainWallet.fxml")
public class MainWalletController implements Initializable {

    @Autowired
    WalletApplication walletApplication;

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
        walletApplication.sceneReceive();
    }

    @FXML
    void pressedSend(ActionEvent event) {
        walletApplication.sceneSend();
    }


    /** <p> Inicializa la ventana cargando las transacciones.
     * De momento no funciona, no se como recuperar la lista de tx.
     * </p>*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelBTCBalance.setText(String.valueOf(WalletApplication.getWallet().getBalance().getValue()));

        /* for (Transaction transaction: transactionsList) {
            paneTransaction(transaction.getDate(), transaction.getValue(), transaction.getAddress());
        } */

    }

    /** <p> Crea un panel para mostrar la informacion de una transaccion </p>*/
    private Pane paneTransaction(Long creationTime, Double btcValue, Address address){

        Pane pane = new Pane();
        pane.setPrefSize(500, 50);
        pane.setStyle("-fx-background-color: #000000;");

        Label dateLabel = new Label("Fecha:");
        dateLabel.setLayoutX(14);
        dateLabel.setLayoutY(6);
        pane.getChildren().add(dateLabel);

        Label date = new Label(Long.toString(creationTime));
        date.setLayoutX(54);
        date.setLayoutY(6);
        pane.getChildren().add(date);

        Label ammountLabel = new Label("Cantidad:");
        ammountLabel.setLayoutX(311);
        ammountLabel.setLayoutY(6);
        pane.getChildren().add(ammountLabel);

        Label ammount = new Label(Double.toString(btcValue));
        ammount.setLayoutX(250);
        ammount.setLayoutY(6);
        pane.getChildren().add(ammount);

        Label addressLabel = new Label("Address:");
        addressLabel.setLayoutX(14);
        addressLabel.setLayoutY(28);
        pane.getChildren().add(addressLabel);

        Label addressA = new Label(address.toString());
        addressA.setLayoutX(68);
        addressA.setLayoutY(28);
        pane.getChildren().add(addressA);

        return pane;

    }
}
