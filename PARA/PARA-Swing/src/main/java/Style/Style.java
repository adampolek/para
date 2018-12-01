package Style;

import javax.swing.*;
import java.awt.*;

public class Style {

    public static void styleButtonSimple(JButton button, int widthImage, int heightImage, int sizeFont) {
        ImageIcon icon = new ImageIcon("style/Button.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(widthImage, heightImage, java.awt.Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(newImage);
        button.setIcon(background);
        button.setRolloverEnabled(true);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Snap ITC", Font.BOLD, sizeFont));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }

    public static void styleTitle(JLabel title, int sizeFont) {
        title.setForeground(Color.red);
        title.setFont(new Font("Snap ITC", Font.BOLD, sizeFont));
        title.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public static void styleTextField(JTextField text, int sizeFont) {
        text.setForeground(Color.red);
        text.setFont(new Font("Snap ITC", Font.BOLD, sizeFont));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBackground(Color.BLACK);
    }
}
