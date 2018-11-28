package Village;

import Characters.Archer;
import Characters.Character;
import Gameplay.Game;
import Village.VillageElement.InnS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillageS extends JFrame implements ActionListener {
    private JButton castleButton = new JButton("Castle");
    private JButton forgeButton = new JButton("Forge");
    private JButton innButton = new JButton("Inn");
    private Game game;
    private JList  heroesList= new JList();
    private DefaultListModel DLM = new DefaultListModel();

    public VillageS(final Game game){
        this.game=game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);
        addButton(castleButton,600,20,150,50);
        addButton(forgeButton,1140,300,150,50);
        addButton(innButton,40,650,150,50);
        addList(heroesList,150,150,400,800);
        //JScrollPane scrollableList = new JScrollPane(heroesList); do scrollowania
        heroesList.setBounds(250,100,400,600);
        heroesList.setVisible(false);

        for (final Character character : game.getInn().getCharacters()) {
            DLM.addElement(character.getName());
            System.out.println(character.getName());
        }
        innButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked Inn");
                heroesList.setModel(DLM);
                heroesList.setVisible(true);
            }
        });
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void addButton(JButton button,int x,int y,int width,int height){
        add(button);
        button.setBounds(x,y,width,height);
       button.addActionListener( this);
    }

    public void addList(JList jlist,int x,int y,int width,int height){
        add(jlist);
        jlist.setBounds(x,y,width,height);
        //jlist.addActionListener( this);
    }



    public void actionPerformed(ActionEvent e) {

    }
}
