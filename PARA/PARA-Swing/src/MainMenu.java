import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    JButton newGameButton = new JButton("New Game");
    JButton loadButton = new JButton("Load game");
    JButton optionsButton = new JButton("Options");
    JButton exitButton = new JButton("Exit");
    JLabel gameTitle = new JLabel("PARA");

    public MainMenu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("PARA");
        setLayout(null);

        addGameTitle();
        addnewGameButton();
        addLoadButton();
        addOptionsButton();
        addExitButton();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == newGameButton){
            System.out.println("nowa gra, jupi!");
        }else if(source == loadButton){
            System.out.println("wczytywanie gry, jupi!");
        }else if(source == optionsButton) {
            System.out.println("Opcje, jupi!");
        }else if(source == exitButton){
            dispose();
        }
    }

    public void addnewGameButton(){
        add(newGameButton);
        newGameButton.setBounds(100,500,200,60);
        newGameButton.addActionListener(this);
    }

    public void addLoadButton(){
        add(loadButton);
        loadButton.setBounds(100,600,200,60);
        loadButton.addActionListener(this);
    }

    public void addOptionsButton(){
        add(optionsButton);
        optionsButton.setBounds(100,700,200,60);
        optionsButton.addActionListener(this);
    }

    public void addExitButton(){
        add(exitButton);
        exitButton.setBounds(100,800,200,60);
        exitButton.addActionListener(this);
    }

    public void addGameTitle(){
        add(gameTitle);
        gameTitle.setBounds(600,200,500,250);
        gameTitle.setForeground(Color.red);
        gameTitle.setFont(new Font("Snap ITC", Font.BOLD, 100));
    }

    public void getAvailableFontNames(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String [] fonts = ge.getAvailableFontFamilyNames();
        for(String f:fonts){
            System.out.println(f);
        }
    }
}
