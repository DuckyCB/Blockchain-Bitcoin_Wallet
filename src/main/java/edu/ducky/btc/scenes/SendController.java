package edu.ducky.btc.scenes;

import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.bitcoinj.core.*;
import org.bitcoinj.wallet.SendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/edu/ducky/btc/scenes/send.fxml")
public class SendController implements Initializable {

    @Autowired
    WalletApplication walletApplication;

    @FXML
    private TextField fieldAddressSend;

    @FXML
    private TextField fieldBTCSend;

    @FXML
    private Label labelBTCValue;

    @FXML
    private Button buttonSend;

    @FXML
    private Label labelBTCAvailable;

    @FXML
    private Button buttonBack;

    /** <p> Vuelve al menu principal </p> */
    @FXML
    void pressedBack(ActionEvent event) {
        walletApplication.sceneMainWallet();
    }

    /**
     * <p> Verifica que los valores del address y la cantidad no sean nulos, crea un address y envia la suma</p>
     */
    @FXML
    void pressedSend(ActionEvent event) throws InsufficientMoneyException {

        if (fieldBTCSend != null & fieldAddressSend != null) {

            Address target = Address.fromString(WalletApplication.getParams(), (String) fieldAddressSend.getCharacters());
            Transaction tx = new Transaction(WalletApplication.getParams());
            Coin coin = Coin.parseCoin(labelBTCValue.getText());
            tx.addOutput(coin, target);
            SendRequest request = SendRequest.forTx(tx);
            try {
                WalletApplication.getWallet().completeTx(request);
                WalletApplication.getWallet().commitTx(request.tx);
                WalletApplication.getPeerGroup().broadcastTransaction(request.tx);
            } catch (InsufficientMoneyException e) {

            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelBTCAvailable.setText(String.valueOf(WalletApplication.getWallet().currentReceiveAddress()));

    }
}
