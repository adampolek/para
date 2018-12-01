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

public class VillageS extends JPanel implements ActionListener {
    private JButton castleButton = new JButton("Castle");
    private JButton forgeButton = new JButton("Forge");
    private JButton innButton = new JButton("Inn");
    private JButton backButton = new JButton("Back");
    private JButton backButtonForge = new JButton("Back");
    private JButton addToTeam = new JButton("Add to your team");
    private JButton upHealth = new JButton("Upgrade health");
    private JButton upAttack = new JButton("Upgrade attack");
    private JButton upDefence = new JButton("Upgrade defence");
    private JButton upSpeed = new JButton("Upgrade speed");
    private JButton upDodge = new JButton("Upgrade dodge");
    private JButton upCritChance = new JButton("Upgrade critical chance");
    private JLabel characterClass = new JLabel();
    private JLabel upgradeMessage = new JLabel();
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
    private int screenWidth;
    private int screenHeight;

    public VillageS(final Game game) {
        this.game = game;
    /*    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);*/
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
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

        backButtonForge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                createVillage();
                //DLM.clear();
                ForgeHeroesList.clearSelection();
                remove(ForgeHeroesList);
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
                remove(characterHealth);
                remove(characterSpeed);
                characterDefence.setVisible(false);
                characterClass.setVisible(false);
                characterHealth.setVisible(false);
                characterDodge.setVisible(false);
                characterCritChance.setVisible(false);
                characterClass.setVisible(false);
                characterAttack.setVisible(false);
            }
        });

        upHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeHp(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
            }
        });

        upAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeAttack(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
            }
        });
        upDefence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeDefence(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
            }
        });
        upSpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeSpeed(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
            }
        });

        upDodge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeDodge(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
            }
        });
        upCritChance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex=ForgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeCritChance(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
                    clearScreen();
                    createForge();
                    upgradeMessage.setText("Upgrade success");
                    upgradeMessage.setForeground(Color.GREEN);
                    upgradeMessage.setVisible(true);
                }
                else {
                    System.out.println("not enough gold");
                    upgradeMessage.setText("Not enough gold");
                    upgradeMessage.setForeground(Color.RED);
                    upgradeMessage.setVisible(true);
                }
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
                    addButton(upHealth,400,100,200,50);
                    upHealth.setVisible(false);
                    addButton(upAttack,400,160,200,50);
                    upAttack.setVisible(false);
                    addButton(upDefence,400,220,200,50);
                    upDefence.setVisible(false);
                    addButton(upSpeed,400,280,200,50);
                    upSpeed.setVisible(false);
                    addButton(upDodge,400,340,200,50);
                    upDodge.setVisible(false);
                    addButton(upCritChance,400,400,200,50);
                    upCritChance.setVisible(false);
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
                        upHealth.setVisible(true);
                        upAttack.setVisible(true);
                        upDefence.setVisible(true);
                        upSpeed.setVisible(true);
                        upDodge.setVisible(true);
                        upCritChance.setVisible(true);
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
        removeAll();
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
        addButton(backButtonForge, 700, 300, 150, 50);
        addLabel(upgradeMessage,700,380,150,15);
        upgradeMessage.setVisible(false);
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
