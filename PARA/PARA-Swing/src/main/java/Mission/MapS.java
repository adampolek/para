package Mission;

import Gameplay.Game;
import Menu.GameMenu;
import Style.Style;
import Village.VillageS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapS extends JPanel {

    private Game game;
    private JPanel[][] cells = new JPanel[25][25];
    private JButton backToMenu = new JButton("Back");
    private int screenWidth;
    private int screenHeight;

    public MapS(Game game) {
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        addBackToMenu();
        createMap();
        pressedKeynord();
    }

    public void addBackToMenu() {
        add(backToMenu);
        backToMenu.setBounds(((int) (screenWidth / 1.1)), (int) (screenHeight / (1.2)), screenWidth / 16, screenHeight / 6);
        Style.styleBackground(backToMenu, "style/Back.png", "style/BackRollOver.png", screenWidth / 16, screenHeight / 6);
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new GameMenu(game, MapS.this));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(MapS.this);
            }
        });
    }

    public void createMap() {
        for (int z = 0; z < 25; z++) {
            for (int i = 0; i < 25; i++) {
                JPanel cell = new JPanel();
                boolean exist = true;
                cell.setBounds(z * screenHeight / 20 + screenWidth / 6, i * screenHeight / 20 + screenHeight / 6, screenHeight / 14, screenHeight / 14);
                cell.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
                switch (game.getHeroControll().getH().getVarible(z, i)) {
                    case 0:
                        exist = false;
                        cells[z][i] = null;
                        break;
                    case 1:
                        cell.setBackground(Color.BLACK);
                        break;
                    case 2:
                        cell.setBackground(Color.BLUE);
                        break;
                    case 3:
                        cell.setBackground(Color.GREEN);
                        break;
                    default:
                        break;
                }
                if (exist) {
                    add(cell);
                    cells[z][i] = cell;
                }
            }
        }
        cells[game.getHeroControll().getH().getX()][game.getHeroControll().getH().getY()].setBackground(Color.RED);
    }

    public void pressedKeynord() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                cells[game.getHeroControll().getH().getX()][game.getHeroControll().getH().getY()].setBackground(Color.BLACK);
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("A") || KeyEvent.getKeyText(e.getKeyCode()).equals("Left")) {
                    game.getHeroControll().goUp();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D") || KeyEvent.getKeyText(e.getKeyCode()).equals("Right")) {
                    game.getHeroControll().goDown();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("W") || KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) {
                    game.getHeroControll().goLeft();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S") || KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) {
                    game.getHeroControll().goRight();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Escape")) {
                    getParent().add(new GameMenu(game, MapS.this));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(MapS.this);
                }
                cells[game.getHeroControll().getH().getX()][game.getHeroControll().getH().getY()].setBackground(Color.RED);
                if (game.getHeroControll().getH().getVarible(game.getHeroControll().getH().getX(), game.getHeroControll().getH().getY()) == 3) {
                    game.backFromMission();
                    getParent().add(new VillageS(game));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(MapS.this);
                } else if (game.getHeroControll().getH().getVarible(game.getHeroControll().getH().getX(), game.getHeroControll().getH().getY()) == 2) {
                    getParent().add(new FightS(game));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(MapS.this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Mission.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
