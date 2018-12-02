package Village.VillageElement;

import Characters.Archer;
import CharactersList.CharactersList;
import Gameplay.Game;
import Style.Style;
import Village.CastleS;
import Village.VillageS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnS extends JPanel{

    private final Game game;
    private JList  heroesList= new JList();
    private int screenWidth;
    private int screenHeight;
    private JPanel innPanel;
    private JList  InnHeroesList= new JList();
    private DefaultListModel DLM = new DefaultListModel();
    private JButton backButton = new JButton("Back");
    CharactersList charactersList;

    public InnS(Game game) {
        this.game = game;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, (int)(screenWidth), screenHeight);
        innPanel = new JPanel();
        innPanel.setLayout(null);
        innPanel.setBackground(Color.BLACK);
        innPanel.setBounds((this.getWidth()-(int)(this.getWidth()/1.2))/2, (this.getHeight()-(int)(this.getHeight()/1.2))/2, (int)(this.getWidth()/1.4), (int)(this.getHeight()/1.2));
        innPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.DARK_GRAY));
        add(innPanel);
        createInn();
//        heroesList.setBounds(150,150,400,800);
    }

    public void createInn(){
        innPanel.add(InnHeroesList);
        InnHeroesList.setBounds(innPanel.getWidth()/10 , innPanel.getHeight()/10 , innPanel.getWidth()/3,(int)(innPanel.getHeight()/1.5));
        InnHeroesList.setForeground(Color.ORANGE);
        InnHeroesList.setBackground(Color.DARK_GRAY);
        System.out.println("You clicked Inn");
        DLM.removeAllElements();

        for (int i=0;i<game.getInn().getCharacters().size();i++) {
            DLM.add(i,game.getInn().getCharacters().get(i).getName());
            System.out.println(game.getInn().getCharacters().get(i).getName());
        }
        System.out.println("DLM size "+DLM.size());
        InnHeroesList.setModel(DLM);
        InnHeroesList.setVisible(true);
        charactersList = new CharactersList(game, this);
        charactersList.showList(this);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new VillageS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(InnS.this);
            }
        });
        innPanel.add(backButton);
        Style.styleButtonSimple(backButton, innPanel.getWidth()/ 4, innPanel.getHeight()/ 18, 33);
        backButton.setBounds((int) (innPanel.getWidth() / (1.4)), (int) (innPanel.getHeight() / 1.1), innPanel.getWidth()/ 4, innPanel.getHeight()/ 18);
        backButton.setVisible(true);

    }

    public Game getGame() {
        return game;
    }

}
