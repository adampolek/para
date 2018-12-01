import Menu.ElementsMenu.Options;
import root.MainFrame;
import root.Music;

import java.io.FileNotFoundException;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class Main {
    public static void main(String [] args){
        Music.play();
        try {
            Music.setLevel(Options.loadFromFile() * 0.1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MainFrame mainFrame = new MainFrame();
       // CharactersList charactersList = new CharactersList(mainFrame);
//        Game game = new Game();
//        game.createFirst();
//        VillageS village = new VillageS(game);
//        village.setVisible(true);
    }
}
