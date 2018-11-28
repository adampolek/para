package Village;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Characters.Character;

import java.awt.*;
import java.util.ArrayList;

public class InnController extends Application{

    TextArea textArea;


    @FXML
    private ListView heroes_id = new ListView();

    @Override
    public void start(Stage stage) throws Exception {

        // Create the TextArea
        textArea = new TextArea();
        textArea.setMaxWidth(350);
        textArea.setMaxHeight(350);
        // Create the Label
        Label CharacterLbl = new Label("Select your Hero: ");

        ListView<Character> characters = new ListView<>();
        characters.setPrefSize(150, 120);
        characters.getItems().addAll(createCharacterList());

        characters.setCellFactory(new CharacterCellFactory());
       // characters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Character>();
        HBox selection = new HBox();
        selection.setSpacing(20);
        selection.getChildren().addAll(characters);
        VBox root = new VBox();
        root.setSpacing(10);
        // Set the Style-properties of the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title
        stage.setTitle("A ListView Example with a Cell Factory");
        // Display the Stage
        stage.show();


    }

    // Helper-Method to create an ArrayList of Characters
    private ArrayList<Character> createCharacterList()
    {
        ArrayList<Character> characters = new ArrayList<Character>();
        characters.add(new Character());
        characters.add(new Character());
        characters.add(new Character());
        characters.add(new Character());
        characters.add(new Character());
        return characters;
}
}
