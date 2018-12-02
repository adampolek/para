package Village;

import CharactersList.CharactersList;
import Gameplay.Game;
import Style.Style;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CastleS extends JPanel{
    private JButton smallMap = new JButton("Small map");
    private JButton mediumMap = new JButton("Medium map");
    private JButton bigMap = new JButton("Big map");
    private JButton back = new JButton("Back to village");
    private JLabel yourCharacters  = new JLabel("Your characters");
    private JLabel selectedHeroes  = new JLabel("Selected characters");
    private Game game;
    private int selectedMap=-1;
    private int screenWidth;
    private int screenHeight;
    private JList userCharacters = new JList();
    private JList selectedCharacters = new JList();
    private DefaultListModel DLM = new DefaultListModel();
    CharactersList charactersList;

    public CastleS(final Game game){
        this.game=game;
        charactersList = new CharactersList(game, this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createCastle();
        loadUserCharacters();
        charactersList.showList(this);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new VillageS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(CastleS.this);
            }
        });
        smallMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMap=0;
            }
        });

        mediumMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMap=1;
            }
        });
        bigMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMap=2;
            }
        });
    }

    public void createCastle(){
        addButton(smallMap,30,30,200,50);
        addButton(mediumMap,30,100,200,50);
        addButton(bigMap,30,170,200,50);
        addButton(back,1000,700,200,50);
        addList(userCharacters,280,80,200,500);
        addLabel(yourCharacters,290,30,150,20);
        addList(selectedCharacters,750,80,200,500);
        addLabel(selectedHeroes,760,30,150,20);
        yourCharacters.setForeground(Color.RED);
        selectedHeroes.setForeground(Color.RED);
    }

    public void addButton(JButton button,int x,int y,int width,int height){
        add(button);
        Style.styleButtonSimple(button, width, height, screenHeight / 50);
        button.setBounds(x,y,width,height);
    }
    public void addList(JList jlist,int x,int y,int width,int height){
        add(jlist);
        jlist.setBounds(x,y,width,height);
        jlist.setForeground(Color.ORANGE);
        jlist.setBackground(Color.DARK_GRAY);
        //jlist.addActionListener( this);
    }
    public void addLabel(JLabel jLabel,int x,int y,int width,int height){
        add(jLabel);
        jLabel.setBounds(x,y,width,height);
        //jlist.addActionListener( this);
    }
    public void loadUserCharacters(){
        DLM.removeAllElements();
        for(int i=0;i<game.getUser().getUsersCharacters().size();i++){
            DLM.add(i,game.getUser().getUsersCharacters().get(i).getName());
        }
        userCharacters.setModel(DLM);
    }

}

