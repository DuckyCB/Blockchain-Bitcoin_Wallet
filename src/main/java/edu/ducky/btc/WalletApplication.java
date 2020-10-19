package edu.ducky.btc;

import edu.ducky.btc.scenes.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.bitcoinj.core.*;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class WalletApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    private static Stage primaryStage;
    private static Wallet wallet;
    private static NetworkParameters params = TestNet3Params.get();
    private static PeerGroup peerGroup;

    public static Wallet getWallet() {
        return wallet;
    }
    public static PeerGroup getPeerGroup() {
        return peerGroup;
    }
    public static NetworkParameters getParams() {
        return params;
    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(BtcApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {

        primaryStage = stage;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(BeginWalletController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bienvenido");
        primaryStage.show();

    }

    public void sceneMainWallet() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainWalletController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wallet");
        primaryStage.show();

    }

    public void sceneRestoreWallet() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(RestoreWalletController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurar");
        primaryStage.show();

    }

    public void sceneSend() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(SendController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enviar");
        primaryStage.show();

    }

    public void sceneReceive() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ReceiveController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recibir");
        primaryStage.show();

    }

    public void createWallet() throws BlockStoreException {

        wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);
        BlockChain chain = new BlockChain(params, wallet, new MemoryBlockStore(params));
        peerGroup = new PeerGroup(params, chain);
        peerGroup.addWallet(wallet);
        peerGroup.start();

    }

    public void restoreWallet(String seedCode, String creationTime) throws UnreadableWalletException {

        DeterministicSeed seed = new DeterministicSeed(seedCode, null, "", Long.parseLong(creationTime));
        wallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH);

    }

    public WalletApplication() {
    }
}
