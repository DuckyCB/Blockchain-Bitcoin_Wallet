package edu.ducky.btc.scenes;

import com.google.common.base.Joiner;
import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/edu/ducky/btc/scenes/backupWallet.fxml")
public class BackupWalletController implements Initializable {

    @Autowired
    WalletApplication walletApplication;

    @FXML
    private Button buttonBack;

    @FXML
    private Label labelKey;

    @FXML
    private Label labelMnemonic;

    @FXML
    private Label labelDate;

    @FXML
    private Button buttonRestore;

    /** <p> Vuelve al menu principal </p> */
    @FXML
    void pressedBack(ActionEvent event) {
        walletApplication.sceneMainWallet();
    }

    /** <p> Copia la key al portapapeles </p> */
    @FXML
    void pressedKey(MouseEvent event) {

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(labelKey.getText()));
        clipboard.setContent(content);

    }

    /** <p> Copia la fecha al portapapeles </p> */
    @FXML
    void pressedDate(MouseEvent event) {

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(labelDate.getText()));
        clipboard.setContent(content);

    }

    /** <p> Copia el mnemonic al portapapeles </p> */
    @FXML
    void pressedMnemonic(MouseEvent event) {

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(labelMnemonic.getText()));
        clipboard.setContent(content);

    }

    @FXML
    void pressedRestore(ActionEvent event) {
        walletApplication.sceneRestoreWallet();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Wallet wallet = WalletApplication.getWallet();
        DeterministicSeed seed = wallet.getKeyChainSeed();
        labelKey.setText(Joiner.on(" ").join(seed.getMnemonicCode()));
        labelDate.setText(String.valueOf(seed.getCreationTimeSeconds()));

    }

}
