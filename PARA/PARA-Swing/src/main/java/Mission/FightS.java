package Mission;

import Characters.Character;
import Gameplay.Game;
import Style.Style;

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
    private JButton attack2 = new JButton();
    private JButton attack3 = new JButton();
    private List<JLabel> heroes = new ArrayList<>();
    private List<JButton> enemies = new ArrayList<>();

    public FightS(Game game) {
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setLayout(null);
        setBackground(Color.BLACK);
        setBounds(0, 0, screenWidth, screenHeight);
        createFight();
    }

    public void createFight() {
        game.randomEnemies();
        addAtacks();
        addAttack1();
        addAttack2();
        addAttack3();
        addHeroes();
        addEnemies();
    }

    public void addAtacks() {
        add(attacks);
        attacks.setBounds(10, (int) (screenHeight / 3) * 2, screenWidth - 20, screenHeight / 3 - 10);
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

            }
        });
    }

    public void addHeroes() {

    }

    public void addEnemies() {
        int y = (screenHeight / 3) * 2 / game.getEnemies().size() - 90;
        for (Character character : game.getEnemies()) {
            JButton enemy = new JButton(character.getName());
            add(enemy);
            enemies.add(enemy);
            enemy.setBounds(screenWidth - (screenHeight / 3) * 2 / 3 - 50, y, (screenHeight / 3) * 2 / 3, (screenHeight / 3) * 2 / 3);
            y = y + (screenHeight / 3) * 2 / 3 + 30;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Mission.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
