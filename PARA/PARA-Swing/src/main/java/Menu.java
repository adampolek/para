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
        title.setBounds((screenWidth - screenWidth / 4) / 2, (screenHeight - screenHeight / 4) / 10, screenWidth / 4, screenHeight / 4);
        title.setForeground(Color.red);
        title.setFont(new Font("Snap ITC", Font.BOLD, screenHeight / 11));
    }

    public void addNewGame() {
        add(newGame);
        setStyleButton(newGame);
        newGame.setBounds(screenWidth / 19, screenHeight / 2, screenWidth / 4, screenHeight / 18);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void addLoadGame() {
        add(loadGame);
        setStyleButton(loadGame);
        loadGame.setBounds(screenWidth / 19, screenHeight / 2 + screenHeight / 11, screenWidth / 4, screenHeight / 18);
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void addOptions() {
        add(options);
        setStyleButton(options);
        options.setBounds(screenWidth / 19, screenHeight / 2 + (screenHeight / 11) * 2, screenWidth / 4, screenHeight / 18);
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new NowePanel());
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(Menu.this);
            }
        });
    }

    public void addExit() {
        add(exit);
        setStyleButton(exit);
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

}
