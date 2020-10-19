package edu.ducky.btc.scenes;

import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.bitcoinj.store.BlockStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/edu/ducky/btc/scenes/beginWallet.fxml")
public class BeginWalletController {

    @Autowired
    WalletApplication walletApplication;

    @FXML
    private Button buttonNew;

    @FXML
    private Button buttonRestore;

    @FXML
    void pressedNew(ActionEvent event) {
        try {
            walletApplication.createWallet();
        } catch (BlockStoreException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressedRestore(ActionEvent event) {
        walletApplication.sceneRestoreWallet();
    }


}
