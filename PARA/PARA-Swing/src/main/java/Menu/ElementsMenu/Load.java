package Menu.ElementsMenu;

import Gameplay.Game;
import Menu.Menu;
import Style.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Load extends JPanel {

    private JLabel title = new JLabel("Load Game");
    private JButton save1 = new JButton("Save 1");
    private JButton save2 = new JButton("Save 2");
    private JButton save3 = new JButton("Save 3");
    private JButton save4 = new JButton("Save 4");
    private JButton save5 = new JButton("Save 5");
    private JLabel isSave = new JLabel("Sorry, there is no such a save yet.");
    private JButton back = new JButton("Back");
    private int screenWidth;
    private int screenHeight;

    public Load() {
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

    public void action(String nazwa) {
        Game game = new Game();
        boolean exist = false;
        try {
            exist = game.getUser().loadFromFile(nazwa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exist) {
            getParent().add(new Menu());//TODO - tu zmien new Menu() na wywolanie wioski z parametrem game;
            getParent().repaint();
            getParent().revalidate();
            getParent().remove(Load.this);
        } else {
            isSave.setVisible(true);
        }
    }

    public void addBack() {
        add(back);
        Style.styleButtonSimple(back, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        back.setBounds((int) (screenWidth / (1.4)), (int) (screenHeight / 1.1), screenWidth / 4, screenHeight / 18);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new Menu());
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Load.this);
            }
        });
    }
}
