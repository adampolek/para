package MenuFirst;


import Main.Controller;
import MenuFirst.Elements.NewGameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {

    private Controller parent;
    @FXML
    private GridPane menuScreen;

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    @FXML
    void newGame(ActionEvent event) {
        FXMLLoader menuLoader = new FXMLLoader(this.getClass().getResource("/FXML/MenuFirst/Elements/NewGame.fxml"));

        VBox gameCreate = null;

        try {
            gameCreate = menuLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        NewGameController newGameController = menuLoader.getController();
        newGameController.setParent(parent);

        gameCreate.setAlignment(Pos.CENTER);
        menuScreen.add(gameCreate, 2, 2);
    }

    @FXML
    void loadGame(ActionEvent event) {

    }

    @FXML
    void option(ActionEvent event) {

    }

    @FXML
    void exitGame(ActionEvent event) {
        Platform.exit();
    }

}
