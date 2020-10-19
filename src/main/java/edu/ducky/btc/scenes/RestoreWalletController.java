package edu.ducky.btc.scenes;

import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/edu/ducky/btc/scenes/restoreWallet.fxml")
public class RestoreWalletController {

    @Autowired
    WalletApplication walletApplication;

    @FXML
    private Button buttonRestore;

    @FXML
    private TextField fieldMnemonic;

    @FXML
    private TextField fieldDate;

    @FXML
    void pressedRestore(ActionEvent event) throws UnreadableWalletException {

        if (fieldMnemonic != null & fieldDate != null) {

            walletApplication.restoreWallet(fieldMnemonic.getText(), fieldDate.getText());

        }

    }


}
