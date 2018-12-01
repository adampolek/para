package Menu;

import CharactersList.CharactersList;
import Gameplay.Game;
import Menu.ElementsMenu.Load;
import Menu.ElementsMenu.Options;
import Style.Style;
import Village.VillageS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private JLabel title = new JLabel("PARA");
    private JButton newGame = new JButton("New Game");
    private JButton loadGame = new JButton("Load Game");
    private JButton options = new JButton("Options");
    private JButton exit = new JButton("Exit");
    private int screenWidth;
    private int screenHeight;

    public Menu() {
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
        addNewGame();
        addLoadGame();
        addOptions();
        addExit();
    }

    public void addTitle() {
        add(title);
        title.setBounds(0, (screenHeight - screenHeight / 4) / 10, screenWidth, screenHeight / 4);
        Style.styleTitle(title, screenHeight / 11);
    }

    public void addNewGame() {
        add(newGame);
        Style.styleButtonSimple(newGame, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        newGame.setBounds(screenWidth / 19, screenHeight / 2, screenWidth / 4, screenHeight / 18);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGamepaly();
            }
        });
    }

    public void addLoadGame() {
        add(loadGame);
        Style.styleButtonSimple(loadGame, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        loadGame.setBounds(screenWidth / 19, screenHeight / 2 + screenHeight / 11, screenWidth / 4, screenHeight / 18);
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new Load());
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Menu.this);
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
                getParent().add(new Options());
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Menu.this);
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
                System.exit(0);
            }
        });
    }

    public void setStyleButton(JButton button) {
        ImageIcon icon = new ImageIcon("style/Button.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(screenWidth / 4, screenHeight / 18, java.awt.Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(newImage);
        button.setIcon(background);
        button.setRolloverEnabled(true);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Snap ITC", Font.BOLD, screenHeight / 33));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }

    public void createGamepaly() {
        final JPanel gameplay = new JPanel();
        add(gameplay);
        gameplay.setLayout(null);
        gameplay.setBackground(Color.BLACK);
        gameplay.setBounds(screenWidth / 19 + screenWidth / 4, screenHeight / 2, screenWidth / 2 + 20, screenHeight / 4);
        gameplay.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));
        JLabel nameText = new JLabel("Enter your name:");
        nameText.setBounds(10, 10, gameplay.getWidth() - 10, screenHeight / 11);
        Style.styleTitle(nameText, screenHeight / 22);
        final JTextField name = new JTextField();
        name.setBounds(13, screenHeight / 11, gameplay.getWidth() - 26, screenHeight / 22);
        name.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        Style.styleTextField(name, screenHeight / 33);
        JButton create = new JButton("Create");
        Style.styleButtonSimple(create, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        create.setBounds(10, (screenHeight / 11) * 2 - 10, screenWidth / 4, screenHeight / 18);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().isEmpty()) {
                    name.setText("Stranger");
                }
                Game game = new Game();
                game.createFirst();
                game.getUser().setName(name.getText());
                getParent().add(new VillageS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Menu.this);
            }
        });
        JButton cancel = new JButton("Cancel");
        Style.styleButtonSimple(cancel, screenWidth / 4, screenHeight / 18, screenHeight / 33);
        cancel.setBounds(10 + screenWidth / 4, (screenHeight / 11) * 2 - 10, screenWidth / 4, screenHeight / 18);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gameplay);
                repaint();
                revalidate();
            }
        });
        gameplay.add(nameText);
        gameplay.add(name);
        gameplay.add(create);
        gameplay.add(cancel);
    }

}
