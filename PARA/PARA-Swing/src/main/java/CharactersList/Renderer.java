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
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);

        return this;
    }
}
