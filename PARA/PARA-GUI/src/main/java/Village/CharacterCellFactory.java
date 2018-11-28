package Village;

import Characters.Character;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.control.ListView;


public class CharacterCellFactory implements Callback<ListView<Character>,ListCell<Character>> {
    @Override
    public CharacterCell call(ListView<Character> listView) {
        return new CharacterCell();
    }
}
