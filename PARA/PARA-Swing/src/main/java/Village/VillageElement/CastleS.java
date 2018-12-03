package Village.VillageElement;

import Characters.Character;
import CharactersList.CharactersList;
import Gameplay.Game;
import Mission.MapS;
import Style.Style;
import Village.UsersStatsS;
import Village.VillageS;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class CastleS extends JPanel implements  ActionListener{
    private JButton smallMap = new JButton("Small map");
    private JButton mediumMap = new JButton("Medium map");
    private JButton bigMap = new JButton("Big map");
    private JButton back = new JButton("Back to village");
    private JButton add = new JButton("Add");
    private JButton reset = new JButton("Reset list");
    private JLabel yourCharacters  = new JLabel("Your characters");
    private JLabel selectedHeroes  = new JLabel("Selected characters");
    private JLabel heroMessage  = new JLabel("");
    private Game game;
    private int selectedMap=-1;
    private int screenWidth;
    private int screenHeight;
    private JLabel characterClass = new JLabel();
    private JLabel characterHealth = new JLabel();
    private JLabel characterAttack = new JLabel();
    private JLabel characterDefence = new JLabel();
    private JLabel characterSpeed = new JLabel();
    private JLabel characterDodge = new JLabel();
    private JLabel characterCritChance = new JLabel();
    private JList userCharacters = new JList();
    private JList selectedCharacters = new JList();
    private DefaultListModel DLM = new DefaultListModel();
    private DefaultListModel DLM2 = new DefaultListModel();
    private List<Integer> selected =  new ArrayList<>();
    private int temp;
    CharactersList charactersList;
    private JPanel castlePanel;
    private JLabel mapLabel = new JLabel("Map selected: None");
    private JButton startMission = new JButton("Start mission");

    public CastleS(final Game game){
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBounds(0, 0, (int) (screenWidth), screenHeight);
        setLayout(null);
        setBackground(Color.BLACK);
        castlePanel = new JPanel();
        castlePanel.setLayout(null);
        castlePanel.setBackground(Color.BLACK);
        castlePanel.setBounds((this.getWidth() - (int) (this.getWidth() / 1.2)) / 2, (this.getHeight() - (int) (this.getHeight() / 1.2)) / 2, (int) (this.getWidth() / 1.4), (int) (this.getHeight() / 1.2));
        castlePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        add(castlePanel);
        addKeyListerner();
        createCastle();
        loadUserCharacters();
        add(new UsersStatsS(game));

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
                mapLabel.setText("Map selected: Small map");
            }
        });

        mediumMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMap=1;
                mapLabel.setText("Map selected: Medium map");
            }
        });
        bigMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMap=2;
                mapLabel.setText("Map selected: Big map");
            }
        });

        userCharacters.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("klik na elem listy");
                int index;
                index = userCharacters.getSelectedIndex();
                addButton(add, (int) (castlePanel.getWidth() / 1.9), (int) (castlePanel.getHeight() / 1.7), castlePanel.getWidth() / 6, castlePanel.getHeight() / 16);
                add.setVisible(false);

                if(index!=-1) {
                    addLabel(characterClass, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterHealth, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 2, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterAttack, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 4, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterDefence, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 6, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterSpeed, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 8, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterDodge, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 10, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    addLabel(characterCritChance, castlePanel.getWidth() / 2 + castlePanel.getHeight() / 60, castlePanel.getHeight() / 8 + castlePanel.getHeight() / 30 * 12, castlePanel.getWidth() / 5, castlePanel.getHeight() / 30);
                    Style.styleTitle(characterClass, castlePanel.getHeight() / 25);
                    characterClass.setForeground(new Color(128, 0, 0));
                    Style.styleTitle(characterHealth, castlePanel.getHeight() / 40);
                    characterHealth.setHorizontalAlignment(SwingConstants.LEFT);
                    Style.styleTitle(characterAttack, castlePanel.getHeight() / 40);
                    characterAttack.setHorizontalAlignment(SwingConstants.LEFT);
                    Style.styleTitle(characterDefence, castlePanel.getHeight() / 40);
                    characterDefence.setHorizontalAlignment(SwingConstants.LEFT);
                    Style.styleTitle(characterSpeed, castlePanel.getHeight() / 40);
                    characterSpeed.setHorizontalAlignment(SwingConstants.LEFT);
                    Style.styleTitle(characterDodge, castlePanel.getHeight() / 40);
                    characterDodge.setHorizontalAlignment(SwingConstants.LEFT);
                    Style.styleTitle(characterCritChance, castlePanel.getHeight() / 40);
                    characterCritChance.setHorizontalAlignment(SwingConstants.LEFT);
                    characterHealth.setVisible(true);
                    characterSpeed.setVisible(true);
                    characterDefence.setVisible(true);
                    characterDodge.setVisible(true);
                    characterCritChance.setVisible(true);
                    characterClass.setVisible(true);
                    characterAttack.setVisible(true);
                    //addToTeam.setVisible(true);
                    Character character = game.getUser().getUsersCharacters().get(index);
                    System.out.println(character.getName() + "" + character.getHp());
                    String nameClass = String.valueOf(character.getClass());
                    characterClass.setText(nameClass.substring(17, nameClass.length()));
                    characterAttack.setText("Attack: " + character.getAttack());
                    characterHealth.setText("Health: " + character.getHp());
                    characterDefence.setText("Defence: " + character.getDefence());
                    characterSpeed.setText("Speed: " + character.getSpeed());
                    characterDodge.setText("Dodge: " + character.getDodge());
                    characterCritChance.setText("Crit chance: " + character.getCrit_chance());
                    add.setVisible(true);
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroMessage.setVisible(false);
                if(temp<3){
                int heroIndex=userCharacters.getSelectedIndex();
                if(selected.contains(heroIndex)){
                    heroMessage.setText("You already have this hero");
                    heroMessage.setForeground(Color.RED);
                    heroMessage.setVisible(true);
                }
                else {
                game.getCastle().addCharcters(game.getUser().getUsersCharacters().get(heroIndex));
                //DLM.remove(heroIndex);
                userCharacters.setModel(DLM);
                DLM2.addElement(game.getUser().getUsersCharacters().get(heroIndex).getName());
                selectedCharacters.setModel(DLM2);
                characterDefence.setVisible(false);
                characterClass.setVisible(false);
                characterHealth.setVisible(false);
                characterDodge.setVisible(false);
                characterCritChance.setVisible(false);
                characterSpeed.setVisible(false);
                characterAttack.setVisible(false);
                temp++;
                selected.add(heroIndex);}}
                else {
                    heroMessage.setText("You already have 3 heroes");
                    heroMessage.setForeground(Color.RED);
                    heroMessage.setVisible(true);
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLM2.removeAllElements();
                selectedCharacters.setModel(DLM2);
                game.getCastle().getCharacters().clear();
                temp=0;
                selected.clear();
            }
        });

        startMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected.size() == 0) {
                    heroMessage.setText("Select at least one hero!");
                    heroMessage.setVisible(true);
                    heroMessage.setForeground(Color.red);
                } else {
                    game.loadMaps();
                    game.goMission(game.getCastle().selectMap(selectedMap));
                    getParent().add(new MapS(game));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(CastleS.this);
                }
            }
        });

    }

    public void addKeyListerner() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Escape")) {
                    getParent().add(new VillageS(game));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(CastleS.this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    public void createCastle(){
        addButton(smallMap, castlePanel.getWidth() / 20, castlePanel.getHeight() / 4, castlePanel.getWidth() / 6, castlePanel.getHeight() / 16);
        addButton(mediumMap, castlePanel.getWidth() / 20, castlePanel.getHeight() / 4 + castlePanel.getHeight() / 16 * 2, castlePanel.getWidth() / 6, castlePanel.getHeight() / 16);
        addButton(bigMap, castlePanel.getWidth() / 20, castlePanel.getHeight() / 4 + castlePanel.getHeight() / 16 * 4, castlePanel.getWidth() / 6, castlePanel.getHeight() / 16);
        addButton(back, (int) (castlePanel.getWidth() / (1.4)), (int) (castlePanel.getHeight() / 1.1), castlePanel.getWidth() / 4, castlePanel.getHeight() / 18);
        addButton(startMission, (int) (castlePanel.getWidth() / (1.4)), (int) (castlePanel.getHeight() / 1.2), castlePanel.getWidth() / 4, castlePanel.getHeight() / 18);
        addList(userCharacters, castlePanel.getWidth() / 4, castlePanel.getHeight() / 10, castlePanel.getWidth() / 4, (int) (castlePanel.getHeight() / 1.5));
        addLabel(yourCharacters, castlePanel.getWidth() / 4, castlePanel.getHeight() / 50, castlePanel.getWidth() / 4, castlePanel.getHeight() / 20);
        Style.styleTitle(yourCharacters, castlePanel.getHeight() / 35);
        addList(selectedCharacters, (int) (castlePanel.getWidth() / 1.4), castlePanel.getHeight() / 10, castlePanel.getWidth() / 4, (int) (castlePanel.getHeight() / 1.5));
        addLabel(selectedHeroes, (int) (castlePanel.getWidth() / 1.4), castlePanel.getHeight() / 50, castlePanel.getWidth() / 4, castlePanel.getHeight() / 20);
        Style.styleTitle(selectedHeroes, castlePanel.getHeight() / 35);
        addLabel(heroMessage, castlePanel.getWidth() / 20, (int) (castlePanel.getHeight() / 1.1), (int) (castlePanel.getWidth() / 1.5), castlePanel.getHeight() / 18);
        Style.styleTitle(heroMessage, castlePanel.getHeight() / 20);
        heroMessage.setHorizontalAlignment(SwingConstants.LEFT);
        addLabel(mapLabel, castlePanel.getWidth() / 20, (int) (castlePanel.getHeight() / 1.2), (int) (castlePanel.getWidth() / 1.5), castlePanel.getHeight() / 18);
        Style.styleTitle(mapLabel, castlePanel.getHeight() / 20);
        mapLabel.setHorizontalAlignment(SwingConstants.LEFT);
        heroMessage.setVisible(false);
        yourCharacters.setForeground(Color.RED);
        selectedHeroes.setForeground(Color.RED);
        charactersList = new CharactersList(game, this);
        charactersList.showList(this);
        addButton(reset,(int) (castlePanel.getWidth() / 1.9), (int) (castlePanel.getHeight() / 1.5 ), castlePanel.getWidth() / 6, castlePanel.getHeight() / 16);
    }

    public void addButton(JButton button,int x,int y,int width,int height){
        castlePanel.add(button);
        Style.styleButtonSimple(button, width, height, screenHeight / 50);
        button.setBounds(x,y,width,height);
    }
    public void addList(JList jlist,int x,int y,int width,int height){
        castlePanel.add(jlist);
        jlist.setBounds(x,y,width,height);
        jlist.setForeground(Color.ORANGE);
        jlist.setBackground(Color.DARK_GRAY);
        //jlist.addActionListener( this);
    }
    public void addLabel(JLabel jLabel,int x,int y,int width,int height){
        castlePanel.add(jLabel);
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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Village.png").getImage(), 0,0, (int) (this.getWidth()/1.2),this.getHeight(),this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

