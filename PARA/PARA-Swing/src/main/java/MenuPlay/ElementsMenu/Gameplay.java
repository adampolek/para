package MenuPlay.ElementsMenu;
import Gameplay.Game;
import Village.VillageS;

public class Gameplay {
    public static void main(String [] args){
    //    MainMenu mainMenu = new MainMenu();
       // mainMenu.setVisible(true);
        Game game = new Game();
        game.createFirst();
        game.getInn().addDwarf();

        VillageS village = new VillageS(game);
        village.setVisible(true);

    }

}
