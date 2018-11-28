package Village.VillageElement;

import Gameplay.Game;

import javax.swing.*;

public class InnS extends JComponent{
    private Game game;
    private JList  heroesList= new JList();

    public InnS() {
        heroesList.setBounds(150,150,400,800);
    }
}
