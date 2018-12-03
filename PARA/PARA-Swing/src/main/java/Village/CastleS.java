package Village;

import Characters.Character;
import CharactersList.CharactersList;
import Gameplay.Game;
import Style.Style;

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

    public CastleS(final Game game){
        this.game=game;
        temp=0;
        charactersList = new CharactersList(game, this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createCastle();
        loadUserCharacters();
        addKeyListerner();
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

        userCharacters.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("klik na elem listy");
                int index;
                index = userCharacters.getSelectedIndex();
                addButton(add,500,330,250,50);
                add.setVisible(false);

                if(index!=-1) {
                    addLabel(characterClass, 500, 100, 150, 15);
                    addLabel(characterHealth, 500, 130, 350, 15);
                    addLabel(characterAttack, 500, 160, 350, 15);
                    addLabel(characterDefence, 500, 190, 350, 15);
                    addLabel(characterSpeed, 500, 220, 350, 15);
                    addLabel(characterDodge, 500, 250, 350, 15);
                    addLabel(characterCritChance, 500, 280, 350, 15);
                    characterClass.setForeground(Color.blue);
                    characterHealth.setForeground(Color.blue);
                    characterAttack.setForeground(Color.blue);
                    characterDefence.setForeground(Color.blue);
                    characterDodge.setForeground(Color.blue);
                    characterCritChance.setForeground(Color.blue);
                    characterSpeed.setForeground(Color.blue);
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
                    characterAttack.setText("Attack: " + character.getAttack() + "( Next level: " + character.getAttack_level() * 10 + " gold)");
                    characterHealth.setText("Health: " + character.getHp() + "( Next level: " + character.getHp_level() * 10 + " gold)");
                    characterDefence.setText("Defence: " + character.getDefence() + "( Next level: " + character.getDefence_level() * 10 + " gold)");
                    characterSpeed.setText("Speed: " + character.getSpeed() + "( Next level: " + character.getSpeed_level() * 10 + " gold)");
                    characterDodge.setText("Dodge: " + character.getDodge() + "( Next level: " + character.getDodge_level() * 10 + " gold)");
                    characterCritChance.setText("Crit chance: " + character.getCrit_chance() + "( Next level: " + character.getCrit_chance_level() * 10 + " gold)");
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
        addButton(smallMap,30,30,200,50);
        addButton(mediumMap,30,100,200,50);
        addButton(bigMap,30,170,200,50);
        addButton(back,1000,700,200,50);
        addList(userCharacters,280,80,200,500);
        addLabel(yourCharacters,290,30,150,20);
        addList(selectedCharacters,750,80,200,200);
        addLabel(selectedHeroes,760,30,150,20);
        addLabel(heroMessage,750,300,150,15);
        heroMessage.setVisible(false);
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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Village.png").getImage(), 0,0, (int) (this.getWidth()/1.2),this.getHeight(),this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

