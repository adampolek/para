package Menu.ElementsMenu;

import Menu.Menu;
import Style.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel {

    private JLabel title = new JLabel("Options");
    private JButton save = new JButton("Save");
    private JButton back = new JButton("Back");
    private int screenWidth;
    private int screenHeight;

    public Options() {
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
        addBack();
        addSave();
    }

    public void addTitle() {
        add(title);
        title.setBounds(0, (screenHeight - screenHeight / 4) / 10, screenWidth, screenHeight / 4);
        Style.styleTitle(title, screenHeight / 11);
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

            }
        });
    }
}
