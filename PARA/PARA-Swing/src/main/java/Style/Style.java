package Style;

import javax.swing.*;
import java.awt.*;

public class Style {

    public static void styleButtonSimple(JButton button, int widthImage, int heightImage, int sizeFont) {
        ImageIcon icon = new ImageIcon("style/Button.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(widthImage, heightImage, java.awt.Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(newImage);
        ImageIcon iconRoll = new ImageIcon("style/ButtonRollOver.png");
        Image imageRoll = iconRoll.getImage();
        Image newImageRoll = imageRoll.getScaledInstance(widthImage, heightImage, java.awt.Image.SCALE_SMOOTH);
        ImageIcon backgroundRoll = new ImageIcon(newImageRoll);
        button.setIcon(background);
        button.setRolloverIcon(backgroundRoll);
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
        text.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
    }
    public static void styleListView(JList jlist, int sizeFont) {
        jlist.setForeground(Color.ORANGE);
        jlist.setFont(new Font("Snap ITC", Font.PLAIN, sizeFont));
        jlist.setBackground(Color.BLACK);
        jlist.setSelectionBackground(Color.GREEN);
        jlist.setSelectionForeground(Color.BLUE);
        jlist.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
    }

    public static void styleSilders(JSlider slider) {
        slider.setBackground(Color.BLACK);
        slider.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);

    }

    public static void styleBackground(JButton button, String path,String pathRollOver, int widthImage, int heightImage) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(widthImage, heightImage, java.awt.Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(newImage);
        ImageIcon iconRoll = new ImageIcon(pathRollOver);
        Image imageRoll = iconRoll.getImage();
        Image newImageRoll = imageRoll.getScaledInstance(widthImage, heightImage, java.awt.Image.SCALE_SMOOTH);
        ImageIcon backgroundRoll = new ImageIcon(newImageRoll);
        button.setIcon(background);
        button.setRolloverIcon(backgroundRoll);
        button.setBorder(null);
        button.setText(null);

    }
}
