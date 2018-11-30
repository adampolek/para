import javax.swing.*;

public class MainFrame extends JFrame {

    NowePanel newPanel = new NowePanel();
    Menu m = new Menu();

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        add(newPanel);
        setVisible(true);
    }
}
