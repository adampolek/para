package Village;

import javafx.scene.control.ListCell;
import Characters.Character;

public class CharacterCell extends ListCell<Character> {
    @Override
    public void updateItem(Character item,boolean empty){
        super.updateItem(item,empty);
        int index =this.getIndex();
        String name = null;
        if(item==null || empty){}
        else {
            name=(index+1)+" " +item.getName();
        }
        this.setText(name);
        setGraphic(null);
    }
}
