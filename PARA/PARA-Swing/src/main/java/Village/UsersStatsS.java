package Village;

import Gameplay.Game;
import Style.Style;

import javax.swing.*;
import java.awt.*;

public class UsersStatsS extends JPanel{
    private JLabel name= new JLabel();
    private JLabel gold = new JLabel();
    private int screenWidth;
    private int screenHeight;
    private Game game;

    public UsersStatsS(Game game){
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        showPanel();
    }

    public void showPanel(){
        setBounds(screenWidth/192, (int)(screenHeight/(1.1)), screenWidth/4, screenHeight/ 12);
        setBackground(Color.black);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        setLayout(null);
        setVisible(true);
        add(name);
        add(gold);
        name.setBounds(0, 0, getWidth(), getHeight()/2);
        Style.styleTitle(name, getHeight()/3);
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setText(" Hello " + game.getUser().getName() + "!");
        name.setVisible(true);
        gold.setBounds(0, getHeight()/2, getWidth(), getHeight()/2);
        Style.styleTitle(gold, getHeight()/3);
        gold.setHorizontalAlignment(SwingConstants.LEFT);
        gold.setText(" Gold: " + game.getUser().getGold());
        gold.setVisible(true);
    }
}
