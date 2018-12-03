package Menu.ElementsMenu;

import Gameplay.Game;
import Menu.GameMenu;
import Style.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameSave extends JPanel {

    private JLabel title = new JLabel("Save Game");
    private JButton save1 = new JButton("Save 1");
    private JButton save2 = new JButton("Save 2");
    private JButton save3 = new JButton("Save 3");
    private JButton save4 = new JButton("Save 4");
    private JButton save5 = new JButton("Save 5");
    private JLabel isSave = new JLabel("Game saved!");
    private JButton back = new JButton("Back");
    private Game game;
    private JPanel backPanel;
    private int screenWidth;
    private int screenHeight;

    public GameSave(Game game, JPanel backPanel) {
        this.backPanel = backPanel;
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createLoad();
    }

    public void createLoad() {
        addTitle();
        addSave1();
        addSave2();
        addSave3();
        addSave4();
        addSave5();
        addIsSave();
        addBack();

    }

    public void addTitle() {
        add(title);
        title.setBounds(0, (screenHeight - screenHeight / 4) / 10, screenWidth, screenHeight / 4);
        Style.styleTitle(title, screenHeight / 11);
    }

    public void addSave1() {
        add(save1);
        Style.styleButtonSimple(save1, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save1.setBounds(screenWidth / 2 - (screenWidth / 8), (int) (screenHeight / 2.5), screenWidth / 4, screenHeight / 18);
        save1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action("save0.txt");
            }
        });
    }

    public void addSave2() {
        add(save2);
        Style.styleButtonSimple(save2, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save2.setBounds(screenWidth / 2 - (screenWidth / 8), (int) (screenHeight / 2.5 + screenHeight / 11), screenWidth / 4, screenHeight / 18);
        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action("save1.txt");
            }
        });
    }

    public void addSave3() {
        add(save3);
        Style.styleButtonSimple(save3, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save3.setBounds(screenWidth / 2 - (screenWidth / 8), (int) (screenHeight / 2.5 + (screenHeight / 11) * 2), screenWidth / 4, screenHeight / 18);
        save3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action("save2.txt");
            }
        });
    }

    public void addSave4() {
        add(save4);
        Style.styleButtonSimple(save4, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save4.setBounds(screenWidth / 2 - (screenWidth / 8), (int) (screenHeight / 2.5 + (screenHeight / 11) * 3), screenWidth / 4, screenHeight / 18);
        save4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action("save3.txt");
            }
        });
    }

    public void addSave5() {
        add(save5);
        Style.styleButtonSimple(save5, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save5.setBounds(screenWidth / 2 - (screenWidth / 8), (int) (screenHeight / 2.5 + (screenHeight / 11) * 4), screenWidth / 4, screenHeight / 18);
        save5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action("save4.txt");
            }
        });
    }

    public void addIsSave() {
        add(isSave);
        isSave.setBounds(10, screenHeight - screenHeight / 11, screenWidth / 2, screenHeight / 11);
        Style.styleTitle(isSave, screenHeight / 33);
        isSave.setHorizontalAlignment(SwingConstants.LEFT);
        isSave.setVisible(false);
    }

    public void action(String name) {
        try {
            game.getUser().saveToFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        isSave.setVisible(true);
    }

    public void addBack() {
        add(back);
        Style.styleButtonSimple(back, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        back.setBounds((int) (screenWidth / (1.4)), (int) (screenHeight / 1.1), screenWidth / 4, screenHeight / 18);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new GameMenu(game, backPanel));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(GameSave.this);
            }
        });
    }
}
