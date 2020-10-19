package edu.ducky.btc;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BtcApplication {

    public static void main(String[] args) {
        Application.launch(WalletApplication.class, args);
    }

}
