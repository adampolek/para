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
    JButton backButton = new JButton("Back");
    JButton saveButton = new JButton("Save");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = (int) screenSize.getHeight();
    int screenWidth = (int) screenSize.getWidth();
    int buttonsScreenWidth = screenWidth/19;
    int buttonsScreenHeight = screenHeight/2;

    public MainMenu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);

        createMenu();

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == newGameButton){
            clearScreen();
            addBackButton();
        }else if(source == loadButton){
            clearScreen();
            addBackButton();
        }else if(source == optionsButton) {
            clearScreen();
            addBackButton();
            addSaveButton();
        }else if(source == exitButton){
            dispose();
        }else if(source == backButton){
            clearScreen();
            createMenu();
        }else if(source == saveButton){
            System.out.println("Zapiśik, jeeeeej!!!");
        }
    }

    public void addnewGameButton(){
        add(newGameButton);
        newGameButton.setBounds(buttonsScreenWidth, buttonsScreenHeight,screenWidth/9,screenHeight/18);
        newGameButton.addActionListener(this);
    }

    public void addLoadButton(){
        add(loadButton);
        loadButton.setBounds(buttonsScreenWidth, buttonsScreenHeight + screenHeight/11,screenWidth/9,screenHeight/18);
        loadButton.addActionListener(this);
    }

    public void addOptionsButton(){
        add(optionsButton);
        optionsButton.setBounds(buttonsScreenWidth, buttonsScreenHeight + (screenHeight/11)*2,screenWidth/9,screenHeight/18);
        optionsButton.addActionListener(this);
    }

    public void addExitButton(){
        add(exitButton);
        exitButton.setBounds(buttonsScreenWidth, buttonsScreenHeight + (screenHeight/11)*3,screenWidth/9,screenHeight/18);
        exitButton.addActionListener(this);
    }

    public void addGameTitle(){
        add(gameTitle);
        gameTitle.setBounds((screenWidth-screenWidth/4)/2,(screenHeight - screenHeight/4)/10,screenWidth/4,screenHeight/4);
        gameTitle.setForeground(Color.red);
        gameTitle.setFont(new Font("Snap ITC", Font.BOLD, screenHeight/11));
    }

    public void addBackButton(){
        add(backButton);
        backButton.setBounds((int)(screenWidth/(1.2)), (int)(screenHeight/1.1), screenWidth/9, screenHeight/18);
        backButton.addActionListener(this);
    }

    public void addSaveButton(){
        add(saveButton);
        saveButton.setBounds((int)(screenWidth/(1.4)), (int)(screenHeight/1.1), screenWidth/9, screenHeight/18);
        saveButton.addActionListener(this);
    }

    public void getAvailableFontNames(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String [] fonts = ge.getAvailableFontFamilyNames();
        for(String f:fonts){
            System.out.println(f);
        }
    }

    public void clearScreen(){
        getContentPane().removeAll();
        repaint();
    }

    public void createMenu(){
        addGameTitle();
        addnewGameButton();
        addLoadButton();
        addOptionsButton();
        addExitButton();
    }
}
