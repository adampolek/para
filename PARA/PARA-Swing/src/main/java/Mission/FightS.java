package Mission;

import Characters.Character;
import Gameplay.Game;
import Style.Style;
import Village.VillageS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FightS extends JPanel {

    private Game game;
    private int screenWidth;
    private int screenHeight;
    private JPanel attacks = new JPanel();
    private JButton attack1 = new JButton("Attack 1");
    private JButton attack2 = new JButton("Area attack");
    private JButton attack3 = new JButton("Attack 2");
    private List<JLabel> heroes = new ArrayList<>();
    private List<JLabel> hpHeroes = new ArrayList<>();
    private List<JLabel> stateHero = new ArrayList<>();
    private List<JLabel> hpEnemis = new ArrayList<>();
    private List<JLabel> stateEnemy = new ArrayList<>();
    private List<JButton> enemies = new ArrayList<>();
    private JLabel selectEnemyAttack = new JLabel("You haven't selected Enemy!");
    private Integer selectEnemy = null;
    private Integer selectQueue = 0;

    public FightS(final Game game) {
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setLayout(null);
        setBackground(Color.BLACK);
        setBounds(0, 0, screenWidth, screenHeight);
        createFight();
        Timer controlFight = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHero.get(0).setText("");
                stateHero.get(1).setText("");
                stateHero.get(2).setText("");
                int lastIndex = game.getQueue().size() - 1;
                if(lastIndex < selectQueue){
                    selectQueue = 0;
                }
                if (game.getEnemies().contains(game.getQueue().get(selectQueue))){
                    for (JButton button : enemies) {
                        if (button.getText().equals(game.getQueue().get(selectQueue).getName())) {
                            button.setBackground(Color.GRAY);
                            repaint();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException eI) {
                                eI.printStackTrace();
                            }
                            Character character = game.enemyRandomAttack(game.getQueue().get(selectQueue));
                            if (character.getHp() < 0) {
                                remove(hpHeroes.get(game.getCharacterInMission().indexOf(character)));
                                game.deletehp(game.getCastle().getCharacters().indexOf(character));
                                game.deadHero(character);
                                repaint();
                                for (JLabel heroButton : heroes) {
                                    if (heroButton.getText().equals(character.getName())) {
                                        hpHeroes.get(heroes.lastIndexOf(heroButton)).setText("Dead");
                                        game.deadHero(character);
                                        heroes.remove(heroButton);
                                        repaint();
                                        break;
                                    }
                                }
                            }
                            if (game.getCharacterInMission().isEmpty()) {
                                game.backFromMission();
                                getParent().add(new VillageS(game));
                                getParent().repaint();
                                getParent().revalidate();
                                getParent().remove(FightS.this);
                            }
                            int select = 0;
                            for (JLabel hp : hpHeroes) {
                                if (!hp.getText().equals("Dead")) {
                                    hp.setText(game.getCharacterInMission().get(select).getHp() + " hp");
                                    select++;
                                }
                            }
                            button.setBackground(Color.BLACK);
                            repaint();
                            selectQueue++;
                            Integer end = game.getQueue().size() - 1;
                            if (end < selectQueue) {
                                selectQueue = 0;
                            }
                            break;
                        }
                    }
                } else {
                    if (game.getQueue().get(selectQueue).getType().equals("Wizard")) {
                        attack1.setText("Attack");
                        attack2.setText("Healing");
                        attack3.setText("Random Healing");
                    } else {
                        attack1.setText("Attack 1");
                        attack2.setText("Area attack");
                        attack3.setText("Attack 2");
                    }
                    stateHero.get(0).setText(game.getQueue().get(selectQueue).getType());
                    stateHero.get(1).setText("Attack: " + game.getQueue().get(selectQueue).getAttack());
                    stateHero.get(2).setText("Defence: " + game.getQueue().get(selectQueue).getDefence());
                }
            }
        });
        controlFight.setDelay(0);
        controlFight.start();
    }

    public void createFight() {
        game.randomEnemies();
        addAtacks();
        addAttack1();
        addAttack2();
        addAttack3();
        addHeroes();
        addEnemies();
        addStateEnemy();
        addStateHero();
        addSelectEnemyAttack();
    }

    public void addAtacks() {
        add(attacks);
        attacks.setBounds(10, (screenHeight / 3) * 2, screenWidth - 20, screenHeight / 3 - 10);
        attacks.setLayout(null);
        attacks.setBackground(Color.BLACK);
        attacks.setBorder(BorderFactory.createMatteBorder(10, 5, 5, 5, Color.DARK_GRAY));
    }

    public void addAttack1() {
        attacks.add(attack1);
        attack1.setBounds(attacks.getWidth() / 2 - screenWidth / 8, attacks.getHeight() / 16, screenWidth / 4, attacks.getHeight() / 4);
        Style.styleButtonSimple(attack1, screenWidth / 4, attacks.getHeight() / 4, screenHeight / 33);
        attack1.setVisible(true);
        attack1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastIndex = game.getQueue().size() - 1;
                if(lastIndex < selectQueue){
                    selectQueue = 0;
                }
                if (selectEnemy == null) {
                    selectEnemyAttack.setVisible(true);
                } else {

                    if (game.getCharacterInMission().contains(game.getQueue().get(selectQueue))) {
                        game.attackHero(game.getQueue().get(selectQueue), game.getEnemies().get(selectEnemy), 0);
                    }
                    selectEnemyAttack.setVisible(false);
                    if (game.getEnemies().get(selectEnemy).getHp() <= 0) {
                        remove(hpEnemis.get(selectEnemy));
                        hpEnemis.remove(hpHeroes.get(selectEnemy));
                        remove(enemies.get(selectEnemy));
                        game.deadEnemy(game.getEnemies().get(selectEnemy));
                        enemies.remove(enemies.get(selectEnemy));
                        repaint();
                        if (game.getEnemies().isEmpty()) {
                            game.changeCellMap();
                            game.getEnemies().clear();
                            game.getQueue().clear();
                            getParent().add(new MapS(game));
                            getParent().repaint();
                            getParent().revalidate();
                            getParent().remove(FightS.this);
                        }
                    } else {
                        for (JLabel hp : hpEnemis) {
                            hp.setText(game.getEnemies().get(hpEnemis.indexOf(hp)).getHp() + " hp");
                        }
                    }
                    selectEnemy = null;
                    stateEnemy.get(0).setText("");
                    stateEnemy.get(1).setText("");
                    stateEnemy.get(2).setText("");
                    selectQueue++;
                    Integer end = game.getQueue().size() - 1;
                    if (end < selectQueue) {
                        selectQueue = 0;
                    }
                }
                stateHero.get(0).setText("");
                stateHero.get(1).setText("");
                stateHero.get(2).setText("");
                changeIndex();
            }
        });
    }

    public void addAttack2() {
        attacks.add(attack2);
        attack2.setBounds(attacks.getWidth() / 2 - screenWidth / 8, attacks.getHeight() / 8 + (attacks.getHeight() / 4), screenWidth / 4, attacks.getHeight() / 4);
        Style.styleButtonSimple(attack2, screenWidth / 4, attacks.getHeight() / 4, screenHeight / 33);
        attack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getQueue().get(selectQueue).getType().equals("Wizard")) {
                    game.attackWizard(game.getQueue().get(selectQueue), 1);
                    for (JLabel hp : hpHeroes) {
                        hp.setText(game.getCharacterInMission().get(hpHeroes.indexOf(hp)).getHp() + " hp");
                    }
                } else {
                    if (game.getCharacterInMission().contains(game.getQueue().get(selectQueue))) {
                        game.attackHero(game.getQueue().get(selectQueue), game.getEnemies().get(game.getEnemies().size() - 1), 1);
                    }
                }
                selectEnemyAttack.setVisible(false);
                for (JLabel hp : hpEnemis) {
                    hp.setText(game.getEnemies().get(hpEnemis.indexOf(hp)).getHp() + " hp");
                }
                selectEnemy = null;
                stateEnemy.get(0).setText("");
                stateEnemy.get(1).setText("");
                stateEnemy.get(2).setText("");
                selectQueue++;
                Integer end = game.getQueue().size() - 1;
                if (end < selectQueue) {
                    selectQueue = 0;
                }
                stateHero.get(0).setText("");
                stateHero.get(1).setText("");
                stateHero.get(2).setText("");
            }
        });
    }

    public void addAttack3() {
        attacks.add(attack3);
        attack3.setBounds(attacks.getWidth() / 2 - screenWidth / 8, attacks.getHeight() / 8 + (attacks.getHeight() / 4) * 2 + attacks.getHeight() / 16, screenWidth / 4, attacks.getHeight() / 4);
        Style.styleButtonSimple(attack3, screenWidth / 4, attacks.getHeight() / 4, screenHeight / 33);
        attack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getQueue().get(selectQueue).getType().equals("Wizard")) {
                    game.attackWizard(game.getQueue().get(selectQueue), 2);
                    for (JLabel hp : hpHeroes) {
                        hp.setText(game.getCharacterInMission().get(hpHeroes.indexOf(hp)).getHp() + " hp");
                    }
                } else if (selectEnemy == null) {
                    selectEnemyAttack.setVisible(true);
                } else {
                    if (game.getCharacterInMission().contains(game.getQueue().get(selectQueue))) {
                        game.attackHero(game.getQueue().get(selectQueue), game.getEnemies().get(selectEnemy), 2);
                    }
                    selectEnemyAttack.setVisible(false);
                    if (game.getEnemies().get(selectEnemy).getHp() < 0) {
                        remove(hpEnemis.get(selectEnemy));
                        hpEnemis.remove(hpHeroes.get(selectEnemy));
                        remove(enemies.get(selectEnemy));
                        game.deadEnemy(game.getEnemies().get(selectEnemy));
                        enemies.remove(enemies.get(selectEnemy));
                        repaint();

                    } else {
                        for (JLabel hp : hpEnemis) {
                            hp.setText(game.getEnemies().get(hpEnemis.indexOf(hp)).getHp() + " hp");
                        }
                    }
                    selectEnemy = null;
                    stateEnemy.get(0).setText("");
                    stateEnemy.get(1).setText("");
                    stateEnemy.get(2).setText("");
                    selectQueue++;
                    Integer end = game.getQueue().size() - 1;
                    if (end < selectQueue) {
                        selectQueue = 0;
                    }
                }
                stateHero.get(0).setText("");
                stateHero.get(1).setText("");
                stateHero.get(2).setText("");
                changeIndex();
            }
        });
    }

    public void addHeroes() {
        int y = (30 + (screenHeight / 3) * 2 - (screenHeight / 7 + 30) * game.getCharacterInMission().size()) / 2;
        for (Character character : game.getCharacterInMission()) {
            JLabel hero = new JLabel(character.getName());
            JLabel hpText = new JLabel(character.getHp() + " hp");
            hpText.setBounds(0, y, (screenHeight / 8) * 2, screenHeight / 7);
            Style.styleTitle(hpText, screenHeight / 24);
            hpText.setHorizontalAlignment(SwingConstants.RIGHT);
            add(hpText);
            hpHeroes.add(hpText);
            add(hero);
            heroes.add(hero);
            hero.setBounds((screenHeight / 7) * 2, y, screenHeight / 7, screenHeight / 7);
            hero.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
            hero.setOpaque(true);
            hero.setForeground(Color.WHITE);
            hero.setHorizontalAlignment(SwingConstants.CENTER);
            hero.setBackground(Color.BLACK);
            y = y + screenHeight / 7 + 30;
        }
    }

    public void addEnemies() {
        int y = (30 + (screenHeight / 3) * 2 - (screenHeight / 7 + 30) * game.getEnemies().size()) / 2;
        for (final Character character : game.getEnemies()) {
            final JButton enemy = new JButton(character.getName());
            JLabel hpText = new JLabel(character.getHp() + " hp");
            hpText.setBounds(screenWidth - (screenHeight / 8) * 2, y, (screenHeight / 8) * 2, screenHeight / 7);
            Style.styleTitle(hpText, screenHeight / 24);
            hpText.setHorizontalAlignment(SwingConstants.LEFT);
            add(hpText);
            hpEnemis.add(hpText);
            add(enemy);
            enemies.add(enemy);
            enemy.setBounds(screenWidth - (screenHeight / 7) * 3, y, screenHeight / 7, screenHeight / 7);
            enemy.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
            enemy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stateEnemy.get(0).setText(character.getType());
                    stateEnemy.get(1).setText("Attack: " + character.getAttack());
                    stateEnemy.get(2).setText("Defence: " + character.getDefence());
                    selectEnemyAttack.setVisible(false);
                    selectEnemy = enemies.indexOf(enemy);
                }
            });
            enemy.setOpaque(true);
            enemy.setBackground(Color.BLACK);
            enemy.setForeground(Color.WHITE);
            y = y + screenHeight / 7 + 30;
        }
    }

    public void changeIndex() {
        for (final JButton button : enemies) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stateEnemy.get(0).setText(game.getEnemies().get(enemies.indexOf(button)).getType());
                    stateEnemy.get(1).setText("Attack: " + game.getEnemies().get(enemies.indexOf(button)).getAttack());
                    stateEnemy.get(2).setText("Defence: " + game.getEnemies().get(enemies.indexOf(button)).getDefence());
                    selectEnemyAttack.setVisible(false);
                    selectEnemy = enemies.indexOf(button);
                }
            });
        }
    }

    public void addStateEnemy() {
        JLabel typeText = new JLabel();
        attacks.add(typeText);
        stateEnemy.add(typeText);
        typeText.setBounds(attacks.getWidth() / 2 + screenWidth / 7, attacks.getHeight() / 16, screenWidth - (attacks.getWidth() / 2 + screenWidth / 7), attacks.getHeight() / 4);
        Style.styleTitle(typeText, screenHeight / 18);
        JLabel attackText = new JLabel();
        attacks.add(attackText);
        stateEnemy.add(attackText);
        attackText.setBounds(attacks.getWidth() / 2 + screenWidth / 6, attacks.getHeight() / 8 + (attacks.getHeight() / 4), screenWidth - (attacks.getWidth() / 2 + screenWidth / 6), attacks.getHeight() / 4);
        Style.styleTitle(attackText, screenHeight / 24);
        attackText.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel defenceText = new JLabel();
        attacks.add(defenceText);
        stateEnemy.add(defenceText);
        defenceText.setBounds(attacks.getWidth() / 2 + screenWidth / 6, attacks.getHeight() / 8 + (attacks.getHeight() / 4) * 2 + attacks.getHeight() / 16, screenWidth - (attacks.getWidth() / 2 + screenWidth / 6), attacks.getHeight() / 4);
        Style.styleTitle(defenceText, screenHeight / 24);
        defenceText.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void addStateHero() {
        JLabel typeText = new JLabel();
        attacks.add(typeText);
        stateHero.add(typeText);
        typeText.setBounds(0, attacks.getHeight() / 16, screenWidth - (attacks.getWidth() / 2 + screenWidth / 7), attacks.getHeight() / 4);
        Style.styleTitle(typeText, screenHeight / 18);
        JLabel attackText = new JLabel();
        attacks.add(attackText);
        stateHero.add(attackText);
        attackText.setBounds((screenWidth / 6 - screenWidth / 7) * 2, attacks.getHeight() / 8 + (attacks.getHeight() / 4), screenWidth - (attacks.getWidth() / 2 + screenWidth / 6), attacks.getHeight() / 4);
        Style.styleTitle(attackText, screenHeight / 24);
        attackText.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel defenceText = new JLabel();
        attacks.add(defenceText);
        stateHero.add(defenceText);
        defenceText.setBounds((screenWidth / 6 - screenWidth / 7) * 2, attacks.getHeight() / 8 + (attacks.getHeight() / 4) * 2 + attacks.getHeight() / 16, screenWidth - (attacks.getWidth() / 2 + screenWidth / 6), attacks.getHeight() / 4);
        Style.styleTitle(defenceText, screenHeight / 24);
        defenceText.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void addSelectEnemyAttack() {
        add(selectEnemyAttack);
        selectEnemyAttack.setBounds(0, attacks.getHeight() / 18, screenWidth, attacks.getHeight() / 6);
        Style.styleTitle(selectEnemyAttack, screenHeight / 22);
        selectEnemyAttack.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Mission.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
