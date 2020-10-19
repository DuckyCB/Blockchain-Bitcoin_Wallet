package edu.ducky.btc.scenes;

import edu.ducky.btc.WalletApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/edu/ducky/btc/scenes/receive.fxml")
public class ReceiveController implements Initializable {

    @Autowired
    WalletApplication walletApplication;

    @FXML
    private Label fieldBTC;

    @FXML
    private StackPane paneKey;

    @FXML
    private Button buttonBack;

    /** <p> Vuelve al menu principal</p> */
    @FXML
    void pressedBack(ActionEvent event) {
        walletApplication.sceneMainWallet();
    }

    /** <p> Copia al portapapeles el valor de la key</p> */
    @FXML
    void pressedKey(MouseEvent event) {

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(WalletApplication.getWallet().currentReceiveAddress()));
        clipboard.setContent(content);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fieldBTC.setText(String.valueOf(WalletApplication.getWallet().currentReceiveAddress()));

    }
}
