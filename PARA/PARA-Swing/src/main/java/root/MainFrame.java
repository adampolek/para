package root;

import Menu.Menu;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        add(new Menu());
        setLayout(null);
        setVisible(true);
    }
}
