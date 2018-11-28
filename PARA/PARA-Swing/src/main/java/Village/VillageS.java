package Village;

import Characters.Archer;
import Characters.Character;
import Gameplay.Game;
import Village.VillageElement.InnS;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillageS extends JFrame implements ActionListener {
    private JButton castleButton = new JButton("Castle");
    private JButton forgeButton = new JButton("Forge");
    private JButton innButton = new JButton("Inn");
    private JLabel characterName = new JLabel();
    private JLabel characterHealth = new JLabel();
    private JLabel characterAttack = new JLabel();
    private JLabel characterDefence = new JLabel();
    private JLabel characterSpeed = new JLabel();
    private JLabel characterDodge = new JLabel();
    private JLabel characterCritChance = new JLabel();
    private Game game;
    private JList  heroesList= new JList();
    private DefaultListModel DLM = new DefaultListModel();

    public VillageS(final Game game){
        this.game=game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);
        addButton(castleButton,600,20,150,50);
        addButton(forgeButton,1140,300,150,50);
        addButton(innButton,40,650,150,50);
        addList(heroesList,150,150,220,800);
        addLabel(characterName,700,150,150,15);
        addLabel(characterHealth,700,170,350,15);
        addLabel(characterAttack,700,190,350,15);
        addLabel(characterDefence,700,210,350,15);
        addLabel(characterSpeed,700,230,350,15);
        addLabel(characterDodge,700,250,350,15);
        addLabel(characterCritChance,700,270,350,15);
        //JScrollPane scrollableList = new JScrollPane(heroesList); do scrollowania
        heroesList.setBounds(250,100,400,600);
        heroesList.setVisible(false);

        for (final Character character : game.getInn().getCharacters()) {
            DLM.addElement(character.getName());
            System.out.println(character.getName());
        }
        innButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked Inn");
                heroesList.setModel(DLM);
                heroesList.setVisible(true);
            }
        });

        heroesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("klik na elem listy");
                for (final Character character : game.getInn().getCharacters()) {
                    String nameClass = String.valueOf(character.getClass());
                    characterName.setText("Name "+character.getName());
                    characterAttack.setText("Attack: " + character.getAttack() + "( Next level: " + character.getAttack_level() * 10 + " gold)");
                    characterHealth.setText("Health: " + character.getHp() + "( Next level: " + character.getHp_level() * 10 + " gold)");
                    characterDefence.setText("Defence: " + character.getDefence() + "( Next level: " + character.getDefence_level() * 10 + " gold)");
                    characterSpeed.setText("Speed: " + character.getSpeed() + "( Next level: " + character.getSpeed_level() * 10 + " gold)");
                    characterDodge.setText("Dodge: " + character.getDodge() + "( Next level: " + character.getDodge_level() * 10 + " gold)");
                    characterCritChance.setText("Crit chance: " + character.getCrit_chance() + "( Next level: " + character.getCrit_chance_level() * 10 + " gold)");
                }
            }
        });


    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void addButton(JButton button,int x,int y,int width,int height){
        add(button);
        button.setBounds(x,y,width,height);
       button.addActionListener( this);
    }

    public void addList(JList jlist,int x,int y,int width,int height){
        add(jlist);
        jlist.setBounds(x,y,width,height);
        //jlist.addActionListener( this);
    }
    public void addLabel(JLabel jLabel,int x,int y,int width,int height){
        add(jLabel);
        jLabel.setBounds(x,y,width,height);
        //jlist.addActionListener( this);
    }



    public void actionPerformed(ActionEvent e) {

    }
}
