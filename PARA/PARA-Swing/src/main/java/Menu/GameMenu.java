package Menu;

import Gameplay.Game;
import Menu.ElementsMenu.GameSave;
import Menu.ElementsMenu.Options;
import Style.Style;
import Village.VillageS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel {

    private JLabel title = new JLabel("Menu");
    private JButton resume = new JButton("Resume");
    private JButton saveGame = new JButton("Save Game");
    private JButton options = new JButton("Options");
    private JButton exit = new JButton("Exit");
    private Game game;
    private JPanel backPanel;
    private int screenWidth;
    private int screenHeight;

    public GameMenu(Game game, JPanel backPanel) {
        this.backPanel = backPanel;
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createMenu();
    }

    public void createMenu() {
        addTitle();
        addResume();
        addSaveGame();
        addOptions();
        addExit();
    }

    public void addTitle() {
        add(title);
        title.setBounds(0, (screenHeight - screenHeight / 4) / 10, screenWidth, screenHeight / 4);
        Style.styleTitle(title, screenHeight / 11);
    }

    public void addResume() {
        add(resume);
        Style.styleButtonSimple(resume, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        resume.setBounds(screenWidth / 19, screenHeight / 2, screenWidth / 4, screenHeight / 18);
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(backPanel);
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(GameMenu.this);
            }
        });
    }

    public void addSaveGame() {
        add(saveGame);
        Style.styleButtonSimple(saveGame, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        saveGame.setBounds(screenWidth / 19, screenHeight / 2 + screenHeight / 11, screenWidth / 4, screenHeight / 18);
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new GameSave(game, backPanel));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(GameMenu.this);
            }
        });
    }

    public void addOptions() {
        add(options);
        Style.styleButtonSimple(options, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        options.setBounds(screenWidth / 19, screenHeight / 2 + (screenHeight / 11) * 2, screenWidth / 4, screenHeight / 18);
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new Options(game, backPanel));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(GameMenu.this);
            }
        });
    }

    public void addExit() {
        add(exit);
        Style.styleButtonSimple(exit, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        exit.setBounds(screenWidth / 19, screenHeight / 2 + (screenHeight / 11) * 3, screenWidth / 4, screenHeight / 18);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameClass = String.valueOf(backPanel.getClass());
                if (nameClass.equals("class Village.VillageS")) {
                    getParent().add(new Menu());
                } else {
                    game.backFromMission();
                    getParent().add(new VillageS(game));
                }
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(GameMenu.this);
            }
        });
    }

}

