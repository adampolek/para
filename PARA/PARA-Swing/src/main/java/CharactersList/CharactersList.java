package CharactersList;

import javax.swing.*;
import java.awt.*;

public class CharactersList{

    DefaultListModel listModel;
    JList list;
    private int screenWidth;
    private int screenHeight;


    public CharactersList(){
        listModel = new DefaultListModel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }

    public void addHeroesToList(){
        listModel.addElement(new Item("name1", new ImageIcon("warrior.png")));
        listModel.addElement(new Item("name2", new ImageIcon("dwarf.png")));
        listModel.addElement(new Item("name3", new ImageIcon("wizard.png")));
        listModel.addElement(new Item("name4", new ImageIcon("archer.png")));
        list = new JList(listModel);
        list.setCellRenderer(new Renderer());
    }

    public void showList(JPanel panel){
        panel.add(list);
        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
    }

    public void showListInFrame(JFrame frame){
        frame.add(list);
        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
    }
}
