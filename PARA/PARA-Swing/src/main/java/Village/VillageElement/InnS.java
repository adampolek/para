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

public class InnS extends JPanel{

    private final Game game;
    private JList  heroesList= new JList();
    private int screenWidth;
    private int screenHeight;
    private JPanel innPanel;
    private JList innHeroesList = new JList();
    private DefaultListModel DLM = new DefaultListModel();
    private JButton backButton = new JButton("Back");
    private JButton addToTeam = new JButton("Add to team");
    private JLabel characterClass = new JLabel();
    private JLabel upgradeMessage = new JLabel();
    private JLabel characterHealth = new JLabel();
    private JLabel characterAttack = new JLabel();
    private JLabel characterDefence = new JLabel();
    private JLabel characterSpeed = new JLabel();
    private JLabel characterDodge = new JLabel();
    private JLabel characterCritChance = new JLabel();
    private JLabel innName = new JLabel();
    CharactersList charactersList;
    JLabel imageLabel;

    public InnS(Game game) {
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBounds(0, 0, (int)(screenWidth), screenHeight);
        setLayout(null);
        setBackground(Color.BLACK);
        innPanel = new JPanel();
        innPanel.setLayout(null);
        innPanel.setBackground(Color.BLACK);
        innPanel.setBounds((this.getWidth()-(int)(this.getWidth()/1.2))/2, (this.getHeight()-(int)(this.getHeight()/1.2))/2, (int)(this.getWidth()/1.4), (int)(this.getHeight()/1.2));
        innPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        add(innPanel);
        addKeyListerner();
        createInn();
//        heroesList.setBounds(150,150,400,800);
    }

    public void createInn(){
        imageLabel = new JLabel();
        innPanel.add(innHeroesList);
        innHeroesList.setBounds(innPanel.getWidth()/10 , innPanel.getHeight()/10 , innPanel.getWidth()/3,(int)(innPanel.getHeight()/1.5));
        innHeroesList.setForeground(Color.ORANGE);
        innHeroesList.setBackground(Color.DARK_GRAY);
        innPanel.add(innName);
        innName.setText("Inn");
        Style.styleTitle(innName, innPanel.getHeight() / 18);
        innName.setBounds(innPanel.getWidth() / 10, innPanel.getHeight() / 40, innPanel.getWidth() / 4, innPanel.getHeight() / 15);
        System.out.println("You clicked Inn");
        DLM.removeAllElements();

        for (int i=0;i<game.getInn().getCharacters().size();i++) {
            DLM.add(i,game.getInn().getCharacters().get(i).getName());
            System.out.println(game.getInn().getCharacters().get(i).getName());
        }
        System.out.println("DLM size "+DLM.size());
        innHeroesList.setModel(DLM);
        innHeroesList.setVisible(true);
        charactersList = new CharactersList(game, this);
        charactersList.showList(this);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new VillageS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(InnS.this);
            }
        });
        innPanel.add(backButton);
        Style.styleButtonSimple(backButton, innPanel.getWidth()/ 4, innPanel.getHeight()/ 18, screenWidth/58);
        backButton.setBounds((int) (innPanel.getWidth() / (1.4)), (int) (innPanel.getHeight() / 1.1), innPanel.getWidth()/ 4, innPanel.getHeight()/ 18);
        backButton.setVisible(true);
        addToTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int heroIndex= innHeroesList.getSelectedIndex();
                game.addCharacterToUser(game.getInn().getCharacters().get(heroIndex));
                DLM.remove(heroIndex);
                innHeroesList.setModel(DLM);
                remove(characterHealth);
                remove(characterSpeed);
                characterDefence.setVisible(false);
                characterClass.setVisible(false);
                characterHealth.setVisible(false);
                characterDodge.setVisible(false);
                characterCritChance.setVisible(false);
                characterSpeed.setVisible(false);
                characterAttack.setVisible(false);
                imageLabel.setIcon(null);
                innPanel.repaint();
            }
        });
        innHeroesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("klik na elem listy");
                innPanel.add(addToTeam);
                Style.styleButtonSimple(addToTeam, innPanel.getWidth()/ 4, innPanel.getHeight()/ 18, screenWidth/58);
                addToTeam.setBounds((int) (innPanel.getWidth() / (2.2)), (int) (innPanel.getHeight() / 1.1), innPanel.getWidth()/ 4, innPanel.getHeight()/ 18);
                addToTeam.setVisible(false);
                int index;
                index = innHeroesList.getSelectedIndex();
                if(index!=-1) {
                    innPanel.add(characterClass);
                    innPanel.add(characterHealth);
                    innPanel.add(characterAttack);
                    innPanel.add(characterDefence);
                    innPanel.add(characterSpeed);
                    innPanel.add(characterDodge);
                    innPanel.add(characterCritChance);
                    imageLabel.setIcon(new ImageIcon(getImageName(game.getInn().getCharacters().get(index).getType())));
                    imageLabel.setBounds(innPanel.getWidth()/2,innPanel.getHeight()/10, innPanel.getWidth()/10, innPanel.getHeight()/9);
                    innPanel.add(imageLabel);
                    characterClass.setBounds(innPanel.getWidth()/2, innPanel.getHeight()/4, innPanel.getWidth()/2, innPanel.getHeight()/20);
                    characterHealth.setBounds(innPanel.getWidth()/2, innPanel.getHeight()/4 + innPanel.getHeight()/25 *2, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterAttack.setBounds(innPanel.getWidth()/2, innPanel.getHeight()/4 + innPanel.getHeight()/25 * 3, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterDefence.setBounds(innPanel.getWidth()/2 , innPanel.getHeight()/4 + innPanel.getHeight()/25 * 4, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterDodge.setBounds(innPanel.getWidth()/2 , innPanel.getHeight()/4 + innPanel.getHeight()/25 * 5, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterCritChance.setBounds(innPanel.getWidth()/2 , innPanel.getHeight()/4 + innPanel.getHeight()/25 * 6, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterSpeed.setBounds(innPanel.getWidth()/2 , innPanel.getHeight()/4 + innPanel.getHeight()/25 * 7, innPanel.getWidth()/2, innPanel.getHeight()/50);
                    characterClass.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/48));
                    characterClass.setForeground(new Color(128,0,0));
                    characterHealth.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/96));
                    characterHealth.setForeground(Color.red);
                    characterAttack.setForeground(Color.red);
                    characterAttack.setFont((new Font("Snap ITC", Font.BOLD, screenWidth/96)));
                    characterDefence.setForeground(Color.red);
                    characterDefence.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/96));
                    characterDodge.setForeground(Color.red);
                    characterDodge.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/96));
                    characterCritChance.setForeground(Color.red);
                    characterCritChance.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/96));
                    characterSpeed.setForeground(Color.red);
                    characterSpeed.setFont(new Font("Snap ITC", Font.BOLD, screenWidth/96));
                    characterHealth.setVisible(true);
                    characterSpeed.setVisible(true);
                    characterDefence.setVisible(true);
                    characterDodge.setVisible(true);
                    characterCritChance.setVisible(true);
                    characterClass.setVisible(true);
                    characterAttack.setVisible(true);
                    addToTeam.setVisible(true);
                    innPanel.setVisible(true);
                    innPanel.repaint();
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
                    getParent().remove(InnS.this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    public String getImageName(String klasa){
        if (klasa.equals("Dwarf")) {
            return "style/dwarf.png";
        } else if (klasa.equals("Archer")) {
            return "style/archer.png";
        } else if (klasa.equals("Warrior")) {
            return "style/warrior.png";
        } else if (klasa.equals("Wizard")) {
            return "style/wizard.png";
        }else if(klasa.equals("Character")){
            return "style/character.png";
        } else {
            return "oh, crap!";
        }
    }

    public Game getGame() {
        return game;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Village.png").getImage(), 0,0, (int) (this.getWidth()/1.2),this.getHeight(),this);
    }

}
