package Village;

import CharactersList.CharactersList;
import Menu.GameMenu;
import Gameplay.Game;
import Style.Style;
import Village.VillageElement.ForgeS;
import Village.VillageElement.InnS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillageS extends JPanel implements ActionListener {
    private JButton castleButton = new JButton("Castle");
    private JButton forgeButton = new JButton("Forge");
    private JButton innButton = new JButton("Inn");
    private JButton backToMenu = new JButton("Back to menu");
    private Game game;
    private int screenWidth;
    private int screenHeight;
    CharactersList charactersList;

    public VillageS(final Game game) {
        this.game = game;
        charactersList = new CharactersList(game, this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        setBounds(0, 0, screenWidth, screenHeight);
        setBackground(Color.BLACK);
        setLayout(null);
        createVillage();
        innButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                getParent().add(new InnS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(VillageS.this);
            }
        });

        forgeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getParent().add(new ForgeS(game));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(VillageS.this);
            }
        });

        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getParent().add(new GameMenu(game, VillageS.this));
                getParent().repaint();
                getParent().revalidate();
                getParent().remove(VillageS.this);
            }
        });
         charactersList.showList(this);

            castleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getParent().add(new CastleS(game));
                    getParent().repaint();
                    getParent().revalidate();
                    getParent().remove(VillageS.this);
                }
            });

    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void addButton(JButton button,int x,int y,int width,int height){
        add(button);
        Style.styleButtonSimple(button, width, height, screenHeight / 50);
        button.setBounds(x,y,width,height);
        button.addActionListener( this);
    }

    public void addList(JList jlist,int x,int y,int width,int height){
        add(jlist);
        jlist.setBounds(x,y,width,height);
        jlist.setForeground(Color.ORANGE);
        jlist.setBackground(Color.DARK_GRAY);
    }
    public void addLabel(JLabel jLabel,int x,int y,int width,int height){
        add(jLabel);
        jLabel.setBounds(x,y,width,height);
    }



    public void actionPerformed(ActionEvent e) {

    }
    public void clearScreen(){
        removeAll();
        repaint();
    }

    public void createVillage(){
        addButton(castleButton,((int)(screenWidth/(double)3.1)),(int)(screenHeight/6.55),screenWidth/5,screenHeight/6);
        Style.styleBackground(castleButton, "style/Castle.png", "style/CastleRollOver.jpg", screenWidth/5, screenHeight/6);
        addButton(forgeButton,screenWidth/2,screenHeight/3,screenWidth/4,screenHeight/3);
        Style.styleBackground(forgeButton, "style/Forge.png", "style/ForgeRollOver.png", screenWidth/4, screenHeight/3);
        addButton(innButton,screenWidth/40,screenHeight/2,screenWidth/4,screenHeight/3);
        Style.styleBackground(innButton, "style/Inn.png", "style/InnRollOver.png", screenWidth/4, screenHeight/3);
        addButton(backToMenu,((int)(screenWidth/(double)2.6)),(int)(screenHeight/(1.2)),screenWidth/16,screenHeight/6);
        Style.styleBackground(backToMenu, "style/Back.png", "style/BackRollOver.png", screenWidth/16, screenHeight/6);
        charactersList.showList(this);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("style/Village.png").getImage(), 0,0, (int) (this.getWidth()/1.2),this.getHeight(),this);
    }

}
