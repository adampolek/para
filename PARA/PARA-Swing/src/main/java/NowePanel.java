import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowePanel extends JPanel {

    private JButton button = new JButton("Click");

    public NowePanel() {
        setBackground(Color.BLUE);
        setBounds(0, 0, 100, 100);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getParent().add(new Menu());
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(NowePanel.this);
            }
        });
        add(button);
    }
}
