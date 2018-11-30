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
        newGame.setBounds(screenWidth / 19, screenHeight / 2, screenWidth / 9, screenHeight / 18);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void addLoadGame() {
        add(loadGame);
        loadGame.setBounds(screenWidth / 19, screenHeight / 2 + screenHeight / 11, screenWidth / 9, screenHeight / 18);
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void addOptions() {
        add(options);
        options.setBounds(screenWidth / 19, screenHeight / 2 + (screenHeight / 11) * 2, screenWidth / 9, screenHeight / 18);
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
        exit.setBounds(screenWidth / 19, screenHeight / 2 + (screenHeight / 11) * 3, screenWidth / 9, screenHeight / 18);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
