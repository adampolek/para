package CharactersList;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JLabel implements ListCellRenderer
{
    private JLabel label;

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Item entry = (Item) value;

        setText(value.toString());
        setIcon(entry.getImg());

        if (isSelected) {
            setBackground(Color.GREEN);
            setForeground(Color.BLUE);
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(new Font("Snap ITC", Font.PLAIN, 16));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        setOpaque(true);

        return this;
    }
}
