package CharactersList;

import Characters.Character;
import Gameplay.Game;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import Style.Style;

public class CharactersList{

    DefaultListModel listModel;
    JList list;
    final JPanel HeroDetails = new JPanel();;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<Character> characters = new ArrayList<>();


    public CharactersList(){
        listModel = new DefaultListModel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }

    public CharactersList(Game game, final JPanel panel){
        listModel = new DefaultListModel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        addHeroesToList(game);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("GOOOL, BRAWO POLAAACYYY!!!!!!");
                createHeroDetail(panel);
            }
        });
    }

    public void addHeroesToList(Game game){
        characters = (ArrayList<Character>) game.getUser().getUsersCharacters();
        for (Character ch: characters) {
            listModel.addElement(new Item(ch.getName(), new ImageIcon(getImageName(ch.getType()))));
        }
        list = new JList(listModel);
        list.setCellRenderer(new Renderer());
    }

    public void showList(JPanel panel){
        panel.add(list);
        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
    }

    public void showListInPanel(JPanel panel){
        panel.add(list);
        list.setBounds(0,0,screenWidth,screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
    }

    public JList getCharacters(){
        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
        return  list;
    }

    public void showList(JFrame panel){
        panel.add(list);
        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
        list.setModel(listModel);
        list.setVisible(true);
    }

//    public void showListInFrame(JFrame frame){
//        frame.add(list);
//        list.setBounds((int)(screenWidth/(double)1.2),0,screenWidth-(int)(screenWidth/(double)1.2),screenHeight);
//        list.setModel(listModel);
//        list.setVisible(true);
//    }

    public String getImageName(String klasa){
        if (klasa.equals("Dwarf")) {
            return "dwarf.png";
        } else if (klasa.equals("Archer")) {
            return "archer.png";
        } else if (klasa.equals("Warrior")) {
            return "warrior.png";
        } else if (klasa.equals("Wizard")) {
            return "wizard.png";
        }else if(klasa.equals("Character")){
            return "character.png";
        } else {
            return "oh, crap!";
        }
    }

    public void createHeroDetail(JPanel frame){
        frame.add(HeroDetails);
        HeroDetails.setBounds(screenWidth/3, screenHeight/3, screenWidth/3, screenHeight/3);
        HeroDetails.setVisible(true);
        HeroDetails.setBackground(Color.black);
        JButton backButton = new JButton("BACK");
        int panelWidth = HeroDetails.getWidth();
        int panelHeight = HeroDetails.getHeight();
        Style.styleButtonSimple(backButton, panelWidth / 4, panelHeight / 18, screenHeight/60);
        backButton.setBounds((int) (panelWidth / (1.4)), (int) (panelHeight / 1.1), panelWidth / 4, panelHeight / 18);
        HeroDetails.add(backButton);
        int index;
        index = list.getSelectedIndex();
        JLabel imageLabel = new JLabel(new ImageIcon(getImageName(characters.get(index).getType())));
        imageLabel.setBounds(panelWidth/40,panelHeight/30, panelHeight/4, panelHeight/4);
        HeroDetails.add(imageLabel);
        JLabel charactersName = new JLabel(characters.get(index).getName());
        charactersName.setBounds(panelWidth/4, panelHeight/30, (int)(panelHeight/(1.2)), panelHeight/4);
        Style.styleTitle(charactersName, screenHeight/40);
        HeroDetails.add(charactersName);
    }
}
