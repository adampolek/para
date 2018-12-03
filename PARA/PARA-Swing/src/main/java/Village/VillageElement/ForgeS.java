package Village.VillageElement;

import Characters.Character;
import CharactersList.CharactersList;
import Gameplay.Game;
import Style.Style;
import Village.VillageS;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private JList forgeHeroesList = new JList();
    private DefaultListModel DLM = new DefaultListModel();
    private int screenWidth;
    private int screenHeight;
    CharactersList charactersList;
    private JPanel forgePanel;
    private JLabel forgeName = new JLabel();

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
        addKeyListerner();
        createForge();
    }

    public void createForge(){
        forgeHeroesList.setBounds(forgePanel.getWidth()/10, forgePanel.getHeight()/10 , forgePanel.getWidth()/3, (int)(forgePanel.getHeight()/1.5));
        forgeHeroesList.setVisible(false);
        forgeHeroesList.setForeground(Color.ORANGE);
        forgeHeroesList.setBackground(Color.DARK_GRAY);
        forgePanel.add(forgeHeroesList);
        forgePanel.add(forgeName);
        forgeName.setText("Forge");
        Style.styleTitle(forgeName, forgePanel.getHeight() / 18);
        forgeName.setBounds(forgePanel.getWidth() / 10, forgePanel.getHeight() / 40, forgePanel.getWidth() / 4, forgePanel.getHeight() / 15);
        forgeHeroesList.setBounds(forgePanel.getWidth()/10 , forgePanel.getHeight()/10 , forgePanel.getWidth()/3,(int)(forgePanel.getHeight()/1.5));
        DLM.removeAllElements();
        for (int i=0;i<game.getUser().getUsersCharacters().size();i++) {
            DLM.add(i,game.getUser().getUsersCharacters().get(i).getName());
            System.out.println(game.getUser().getUsersCharacters().get(i).getName());
        }
        forgePanel.add(backButtonForge);
        Style.styleButtonSimple(backButtonForge, forgePanel.getWidth()/ 4, forgePanel.getHeight()/ 18, 33);
        backButtonForge.setBounds((int) (forgePanel.getWidth() / (1.4)), (int) (forgePanel.getHeight() / 1.1), forgePanel.getWidth()/ 4, forgePanel.getHeight()/ 18);
        backButtonForge.setVisible(true);
        backButtonForge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new VillageS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(ForgeS.this);
            }
        });
        forgePanel.add(upgradeMessage);
        upgradeMessage.setBounds((int)(forgePanel.getWidth() / 10), (int) (forgePanel.getHeight() / 1.1), (int)(forgePanel.getWidth()/ 1.8), forgePanel.getHeight()/ 18);
        Style.styleTitle(upgradeMessage, screenWidth/48);
        upgradeMessage.setVisible(false);
        System.out.println("DLM size "+DLM.size());
        forgeHeroesList.setModel(DLM);
        forgeHeroesList.setVisible(true);
        charactersList = new CharactersList(game, this);
        charactersList.showList(this);
        upHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int heroIndex = forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeHp(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
                int heroIndex=forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeAttack(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
                int heroIndex=forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeDefence(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
                int heroIndex=forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeSpeed(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
                int heroIndex=forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeDodge(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
                int heroIndex=forgeHeroesList.getSelectedIndex();
                boolean to_upgrade=false;
                to_upgrade=game.getForge().upgradeCritChance(game.getUser().getUsersCharacters().get(heroIndex),game.getUser());
                if(to_upgrade){
                    System.out.println("Upgrade success");
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
        forgeHeroesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("klik na elem listy");
                int index;
                index = forgeHeroesList.getSelectedIndex();
                System.out.println(index);
                forgePanel.add(upHealth);
                upHealth.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upHealth, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upHealth.setVisible(false);
                forgePanel.add(upAttack);
                upAttack.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*2, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upAttack, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upAttack.setVisible(false);
                forgePanel.add(upDefence);
                upDefence.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*4, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upDefence, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upDefence.setVisible(false);
                forgePanel.add(upSpeed);
                upSpeed.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*6, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upSpeed, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upSpeed.setVisible(false);
                forgePanel.add(upDodge);
                upDodge.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*8, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upDodge, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upDodge.setVisible(false);
                forgePanel.add(upCritChance);
                upCritChance.setBounds((int)(forgePanel.getWidth()/2.1), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*10, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                Style.styleButtonSimple(upCritChance, forgePanel.getWidth()/4, forgePanel.getHeight()/18, screenWidth/96);
                upCritChance.setVisible(false);
                if(index!=-1) {
                    forgePanel.add(characterClass);
                    characterClass.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/12, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterClass, screenWidth/40);
                    forgePanel.add(characterHealth);
                    characterHealth.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterHealth, screenWidth/80);
                    forgePanel.add(characterAttack);
                    characterAttack.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*2, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterAttack, screenWidth/80);
                    forgePanel.add(characterDefence);
                    characterDefence.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*4, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterDefence, screenWidth/80);
                    forgePanel.add(characterSpeed);
                    characterSpeed.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*6, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterSpeed, screenWidth/80);
                    forgePanel.add(characterDodge);
                    characterDodge.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*8, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterDodge, screenWidth/80);
                    forgePanel.add(characterCritChance);
                    characterCritChance.setBounds((int)(forgePanel.getWidth()/1.4), forgePanel.getHeight()/5 + forgePanel.getHeight()/18*10, forgePanel.getWidth()/4, forgePanel.getHeight()/18);
                    Style.styleTitle(characterCritChance, screenWidth/96);
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
                    characterAttack.setText("Attack: " + character.getAttack() + "(" + character.getAttack_level() * 10 + " gold)");
                    characterHealth.setText("Health: " + character.getHp() + "(" + character.getHp_level() * 10 + " gold)");
                    characterDefence.setText("Defence: " + character.getDefence() + "(" + character.getDefence_level() * 10 + " gold)");
                    characterSpeed.setText("Speed: " + character.getSpeed() + "(" + character.getSpeed_level() * 10 + " gold)");
                    characterDodge.setText("Dodge: " + character.getDodge() + "(" + character.getDodge_level() * 10 + " gold)");
                    characterCritChance.setText("Crit chance: " + character.getCrit_chance() + "(" + character.getCrit_chance_level() * 10 + " gold)");
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
                    getParent().remove(ForgeS.this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    public void clearScreen(){
        removeAll();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Village.png").getImage(), 0,0, (int) (this.getWidth()/1.2),this.getHeight(),this);
    }
}
