package Main;

import MenuFirst.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private BorderPane mainScreen;

    public BorderPane getMainScreen() {
        return mainScreen;
    }

    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader menuLoader = new FXMLLoader(this.getClass().getResource("/FXML/MenuFirst/Menu.fxml"));

        GridPane menu = null;

        try {
            menu = menuLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        MenuController menuController = menuLoader.getController();
        menuController.setParent(this);

        mainScreen.setCenter(menu);
    }
}
