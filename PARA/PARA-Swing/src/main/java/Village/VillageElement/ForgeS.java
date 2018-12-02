package Village.VillageElement;

import CharactersList.CharactersList;
import Gameplay.Game;

import javax.swing.*;
import java.awt.*;

public class ForgeS extends JPanel {

    private JButton backButtonForge = new JButton("Back");
    private JButton upHealth = new JButton("Upgrade health");
    private JButton upAttack = new JButton("Upgrade attack");
    private JButton upDefence = new JButton("Upgrade defence");
    private JButton upSpeed = new JButton("Upgrade speed");
    private JButton upDodge = new JButton("Upgrade dodge");
    private JButton upCritChance = new JButton("Upgrade critical");
    private JLabel characterClass = new JLabel();
    private JLabel upgradeMessage = new JLabel();
    private JLabel characterHealth = new JLabel();
    private JLabel characterAttack = new JLabel();
    private JLabel characterDefence = new JLabel();
    private JLabel characterSpeed = new JLabel();
    private JLabel characterDodge = new JLabel();
    private JLabel characterCritChance = new JLabel();
    private Game game;
    private JList  ForgeHeroesList= new JList();
    private DefaultListModel DLM = new DefaultListModel();
    private int screenWidth;
    private int screenHeight;
    CharactersList charactersList;
    private JPanel forgePanel;

    public ForgeS(Game game){
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBounds(0, 0, (int)(screenWidth), screenHeight);
        setLayout(null);
        setBackground(Color.BLACK);
        forgePanel = new JPanel();
        forgePanel.setLayout(null);
        forgePanel.setBackground(Color.BLACK);
        forgePanel.setBounds((this.getWidth()-(int)(this.getWidth()/1.2))/2, (this.getHeight()-(int)(this.getHeight()/1.2))/2, (int)(this.getWidth()/1.4), (int)(this.getHeight()/1.2));
        forgePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        add(forgePanel);
        createForge();
        charactersList = new CharactersList(game, this);
        charactersList.showList(this);
    }

    public void createForge(){
        ForgeHeroesList.setBounds(forgePanel.getWidth()/10, forgePanel.getHeight()/10 , forgePanel.getWidth()/3, (int)(forgePanel.getHeight()/1.5));
        ForgeHeroesList.setVisible(false);
        ForgeHeroesList.setForeground(Color.ORANGE);
        ForgeHeroesList.setBackground(Color.DARK_GRAY);
    }
}
