package Village;

import Buildings.Forge;
import Characters.Archer;
import Characters.Character;
import CharactersList.CharactersList;
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
    private JButton backButton = new JButton("Back");
    private JButton addToTeam = new JButton("Add to your team");
    private JLabel characterClass = new JLabel();
    private JLabel characterHealth = new JLabel();
    private JLabel characterAttack = new JLabel();
    private JLabel characterDefence = new JLabel();
    private JLabel characterSpeed = new JLabel();
    private JLabel characterDodge = new JLabel();
    private JLabel characterCritChance = new JLabel();
    private Game game;
    private JList  InnHeroesList= new JList();
    private JList  ForgeHeroesList= new JList();
    private DefaultListModel DLM = new DefaultListModel();

    public VillageS(final Game game) {
        this.game = game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);
        createVillage();
//        CharactersList charactersList = new CharactersList();
//        charactersList.addHeroesToList();
//        charactersList.showListInFrame(this);


        //addButton(addToTeam,700,20,150,50);
        //JScrollPane scrollableList = new JScrollPane(InnHeroesList); do scrollowania
        InnHeroesList.setBounds(250, 100, 400, 600);
        InnHeroesList.setVisible(false);

        ForgeHeroesList.setBounds(250, 100, 400, 600);
        ForgeHeroesList.setVisible(false);


        innButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                createInn();
                addButton(backButton, 700, 300, 150, 50);
            }
        });

        forgeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                createForge();
                addButton(backButton, 700, 300, 150, 50);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                createVillage();
                //DLM.clear();
                InnHeroesList.clearSelection();
                remove(InnHeroesList);
                System.out.println("DLM size " + DLM.size());
                System.out.println("List size "+game.getInn().getCharacters().size());
            }
        });

        addToTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int heroIndex=InnHeroesList.getSelectedIndex();
                game.addCharacterToUser(game.getInn().getCharacters().get(heroIndex));
                DLM.remove(heroIndex);
                InnHeroesList.setModel(DLM);
                characterHealth.setVisible(false);
                characterSpeed.setVisible(false);
                characterDefence.setVisible(false);
                characterDodge.setVisible(false);
                characterCritChance.setVisible(false);
                characterClass.setVisible(false);
                characterAttack.setVisible(false);
            }
        });

            InnHeroesList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                        System.out.println("klik na elem listy");
                    addButton(addToTeam, 700, 360, 150, 50);
                    addToTeam.setVisible(false);
                        int index;
                            index = InnHeroesList.getSelectedIndex();
                        if(index!=-1) {
                            addLabel(characterClass, 700, 150, 150, 15);
                            addLabel(characterHealth, 700, 170, 350, 15);
                            addLabel(characterAttack, 700, 190, 350, 15);
                            addLabel(characterDefence, 700, 210, 350, 15);
                            addLabel(characterSpeed, 700, 230, 350, 15);
                            addLabel(characterDodge, 700, 250, 350, 15);
                            addLabel(characterCritChance, 700, 270, 350, 15);
                            characterHealth.setVisible(true);
                            characterSpeed.setVisible(true);
                            characterDefence.setVisible(true);
                            characterDodge.setVisible(true);
                            characterCritChance.setVisible(true);
                            characterClass.setVisible(true);
                            characterAttack.setVisible(true);
                            addToTeam.setVisible(true);
                            Character character = game.getInn().getCharacters().get(index);
                            System.out.println(character.getName() + "" + character.getHp());
                            String nameClass = String.valueOf(character.getClass());
                            characterClass.setText(nameClass.substring(17, nameClass.length()));
                            characterAttack.setText("Attack: " + character.getAttack() + "( Next level: " + character.getAttack_level() * 10 + " gold)");
                            characterHealth.setText("Health: " + character.getHp() + "( Next level: " + character.getHp_level() * 10 + " gold)");
                            characterDefence.setText("Defence: " + character.getDefence() + "( Next level: " + character.getDefence_level() * 10 + " gold)");
                            characterSpeed.setText("Speed: " + character.getSpeed() + "( Next level: " + character.getSpeed_level() * 10 + " gold)");
                            characterDodge.setText("Dodge: " + character.getDodge() + "( Next level: " + character.getDodge_level() * 10 + " gold)");
                            characterCritChance.setText("Crit chance: " + character.getCrit_chance() + "( Next level: " + character.getCrit_chance_level() * 10 + " gold)");
                        }}
            });

            ForgeHeroesList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("klik na elem listy");
                    int index;
                    index = ForgeHeroesList.getSelectedIndex();
                    System.out.println(index);
                    if(index!=-1) {
                        addLabel(characterClass, 700, 150, 150, 15);
                        addLabel(characterHealth, 700, 170, 350, 15);
                        addLabel(characterAttack, 700, 190, 350, 15);
                        addLabel(characterDefence, 700, 210, 350, 15);
                        addLabel(characterSpeed, 700, 230, 350, 15);
                        addLabel(characterDodge, 700, 250, 350, 15);
                        addLabel(characterCritChance, 700, 270, 350, 15);
                        characterHealth.setVisible(true);
                        characterSpeed.setVisible(true);
                        characterDefence.setVisible(true);
                        characterDodge.setVisible(true);
                        characterCritChance.setVisible(true);
                        characterClass.setVisible(true);
                        characterAttack.setVisible(true);
                        addToTeam.setVisible(true);
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
    public void clearScreen(){
        getContentPane().removeAll();
        repaint();
    }
    public void createInn(){
        addList(InnHeroesList,150,100,220,600);
        System.out.println("You clicked Inn");
            DLM.removeAllElements();

            for (int i=0;i<game.getInn().getCharacters().size();i++) {
                DLM.add(i,game.getInn().getCharacters().get(i).getName());
                System.out.println(game.getInn().getCharacters().get(i).getName());
            }
        System.out.println("DLM size "+DLM.size());
            InnHeroesList.setModel(DLM);
            InnHeroesList.setVisible(true);
    }
    public void createForge(){
        addList(ForgeHeroesList,150,100,220,600);
        DLM.removeAllElements();
        for (int i=0;i<game.getUser().getUsersCharacters().size();i++) {
            DLM.add(i,game.getUser().getUsersCharacters().get(i).getName());
            System.out.println(game.getUser().getUsersCharacters().get(i).getName());
        }
        System.out.println("DLM size "+DLM.size());
        ForgeHeroesList.setModel(DLM);
        ForgeHeroesList.setVisible(true);
    }
    public void createVillage(){
        addButton(castleButton,600,20,150,50);
        addButton(forgeButton,1140,300,150,50);
        addButton(innButton,40,650,150,50);
    }
}
