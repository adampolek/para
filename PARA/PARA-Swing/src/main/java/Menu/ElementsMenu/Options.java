package Menu.ElementsMenu;

import Gameplay.Game;
import Menu.Menu;
import Menu.GameMenu;
import Style.Style;
import root.Music;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Options extends JPanel {

    private JLabel title = new JLabel("Options");
    private JLabel volumeText = new JLabel("Volume");
    private JSlider volume;
    private JLabel description1 = new JLabel("Control:");
    private JLabel description2 = new JLabel("Move with arrows");
    private JLabel description3 = new JLabel("Press Enter to select chosen option");
    private JLabel description4 = new JLabel("Press Esc to go back");
    private JLabel description5 = new JLabel("Good luck! :)");
    private JButton save = new JButton("Save");
    private JButton back = new JButton("Back");
    private JPanel backPanel = null;
    private Game game;
    private int screenWidth;
    private int screenHeight;

    public Options() {
        try {
            volume = new JSlider(JSlider.HORIZONTAL, 0, 10,loadFromFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createOptions();
    }

    public Options(Game game, JPanel panel) {
        this.game = game;
        this.backPanel = panel;
        try {
            volume = new JSlider(JSlider.HORIZONTAL, 0, 10,loadFromFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, screenWidth, screenHeight);
        createOptions();
    }

    public void createOptions() {
        addTitle();
        addVolumeText();
        addVolume();
        addDescription();
        addBack();
        addSave();
    }

    public void addTitle() {
        add(title);
        title.setBounds(0, (screenHeight - screenHeight / 4) / 10, screenWidth, screenHeight / 4);
        Style.styleTitle(title, screenHeight / 11);
    }

    public void addVolumeText(){
        add(volumeText);
        volumeText.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 4, screenWidth/ 8, screenHeight / 18);
        Style.styleTitle(volumeText, screenHeight / 33);
    }

    public void addDescription(){
        add(description1);
        description1.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 3, screenWidth, screenHeight / 18);
        Style.styleTitle(description1, screenHeight / 33);
        add(description2);
        description2.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 3 + (screenHeight / 18), screenWidth, screenHeight / 18);
        Style.styleTitle(description2, screenHeight / 33);
        add(description3);
        description3.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 3 + (screenHeight / 18)*2, screenWidth, screenHeight / 18);
        Style.styleTitle(description3, screenHeight / 33);
        add(description4);
        description4.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 3 + (screenHeight / 18)*3, screenWidth, screenHeight / 18);
        Style.styleTitle(description4, screenHeight / 33);
        add(description5);
        description5.setBounds(30, (screenHeight - screenHeight / 4) / 10 + screenHeight / 3 + (screenHeight / 18)*4, screenWidth, screenHeight / 18);
        Style.styleTitle(description5, screenHeight / 33);
    }

    public void addVolume(){
        add(volume);
        volume.setBounds(screenWidth/8 + 60, (screenHeight - screenHeight / 4) / 10 + screenHeight / 4, screenWidth/2, screenHeight / 18);
        volume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Music.setLevel(volume.getValue() * 0.1);
            }
        });
        Style.styleSilders(volume, screenHeight/33);
    }

    public void addBack() {
        add(back);
        Style.styleButtonSimple(back, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        back.setBounds((int) (screenWidth / (1.4)), (int) (screenHeight / 1.1), screenWidth / 4, screenHeight / 18);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Music.setLevel(loadFromFile() * 0.1);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                if(backPanel == null) {
                    getParent().add(new Menu());
                }else {
                    getParent().add(new GameMenu(game, backPanel));
                }
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Options.this);
            }
        });
    }

    public void addSave() {
        add(save);
        Style.styleButtonSimple(save, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        save.setBounds((int) (screenWidth / (2.2)), (int) (screenHeight / 1.1), screenWidth / 4, screenHeight / 18);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("options.txt");
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    writer.write(String.valueOf(volume.getValue()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static int loadFromFile() throws FileNotFoundException {
        File file = new File("options.txt");
        if (!file.exists())
            return 0;
        Scanner scanner = new Scanner(file);
        return scanner.nextInt();
    }
}
