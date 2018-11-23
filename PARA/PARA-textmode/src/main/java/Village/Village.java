package Village;

import Controls.Action;
import Controls.MyButton;
import Gameplay.Game;
import MenuPlay.GameMenu;
import Village.VillageElement.CastleTextMode;
import Village.VillageElement.Equipment;
import Village.VillageElement.ForgeTextMode;
import Village.VillageElement.InnTextMode;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Village {

    private Screen screen;
    private TextGraphics textGraphics;
    private List<MyButton> buildings = new ArrayList<MyButton>();
    private Game game;
    TerminalPosition windowshowstart;
    TerminalPosition windowshowend;

    public void setGame(Game game) {
        this.game = game;
    }

    public Village(final Screen screen) {
        int y = (int) ((screen.getTerminalSize().getRows()) * 0.2);
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        buildings.add(new MyButton(textGraphics, new TerminalPosition(1, y), new Action() {
            @Override
            public boolean run() throws IOException {
                CastleTextMode castleTextMode = new CastleTextMode(screen, windowshowstart, windowshowend, game);
                castleTextMode.choiceMap();
                return false;
            }
        }));
        buildings.get(0).setLabel("Castle");
        buildings.get(0).setWidth(25);
        buildings.get(0).setActive();
        y += 3;

        buildings.add(new MyButton(textGraphics, new TerminalPosition(1, y), new Action() {
            @Override
            public boolean run() throws IOException {
                if (!game.getInn().getCharacters().isEmpty()) {
                    InnTextMode innTextMode = new InnTextMode(screen, windowshowstart, windowshowend, game);
                    innTextMode.choice();
                }
                return false;
            }
        }));
        buildings.get(1).setLabel("Inn");
        buildings.get(1).setWidth(25);
        y += 3;

        buildings.add(new MyButton(textGraphics, new TerminalPosition(1, y), new Action() {
            @Override
            public boolean run() throws IOException {
                if (!game.getUser().getUsersCharacters().isEmpty()) {
                    ForgeTextMode forgeTextMode = new ForgeTextMode(screen, windowshowstart, windowshowend, game);
                    forgeTextMode.choice();
                }
                return false;
            }
        }));
        buildings.get(2).setLabel("Forge");
        buildings.get(2).setWidth(25);
        y += 3;

        buildings.add(new MyButton(textGraphics, new TerminalPosition(1, y), new Action() {
            @Override
            public boolean run() throws IOException {
                if (!game.getUser().getUsersCharacters().isEmpty()) {
                    Equipment equipment = new Equipment(screen, windowshowstart, windowshowend, game);
                    equipment.choice();
                }
                return false;
            }
        }));
        buildings.get(3).setLabel("Equipment");
        buildings.get(3).setWidth(25);

        windowshowstart = new TerminalPosition(28, (int) (((screen.getTerminalSize().getRows()) * 0.2) + 1));
        windowshowend = new TerminalPosition(this.screen.getTerminalSize().getColumns() - 3, screen.getTerminalSize().getRows() - 3);
    }

    public boolean choice() {
        Integer activation = 0;
        boolean run = true;

        while (run) {
            KeyStroke key = null;
            try {
                key = this.screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowUp ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                    this.buildings.get(activation).setActive();
                    if (activation != 0) {
                        activation--;
                    }
                    this.buildings.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    this.buildings.get(activation).setActive();
                    if (activation != this.buildings.size() - 1) {
                        activation++;
                    }
                    this.buildings.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.Escape) {
                    GameMenu gamemenu = new GameMenu(screen, game);
                    gamemenu.create();
                    gamemenu.draw();
                    try {
                        run = gamemenu.choice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    try {
                        this.buildings.get(activation).go();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (key.getKeyType() == KeyType.EOF) {
                    try {
                        this.screen.stopScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                clearWindow();
                this.draw();
            }
        }
        return run;
    }

    public void clearWindow() {
        for (int i = windowshowstart.getColumn(); i <= windowshowend.getColumn(); i++) {
            for (int z = windowshowstart.getRow(); z <= windowshowend.getRow(); z++) {
                textGraphics.putString(new TerminalPosition(i, z), " ");
            }
        }
    }

    public void border() {
        int x = 27, y = (int) ((screen.getTerminalSize().getRows()) * 0.2);

        this.textGraphics.putString(new TerminalPosition(x, y), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(this.screen.getTerminalSize().getColumns() - 2, y), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER));
        this.textGraphics.putString(new TerminalPosition(x, this.screen.getTerminalSize().getRows() - 2), String.valueOf(Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(this.screen.getTerminalSize().getColumns() - 2, this.screen.getTerminalSize().getRows() - 2), String.valueOf(Symbols.DOUBLE_LINE_TOP_LEFT_CORNER));

        for (int i = x + 1; i < this.screen.getTerminalSize().getColumns() - 2; i++) {
            this.textGraphics.putString(new TerminalPosition(i, y), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
            this.textGraphics.putString(new TerminalPosition(i, screen.getTerminalSize().getRows() - 2), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
        }

        for (int i = y + 1; i < this.screen.getTerminalSize().getRows() - 2; i++) {
            this.textGraphics.putString(new TerminalPosition(x, i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
            this.textGraphics.putString(new TerminalPosition(this.screen.getTerminalSize().getColumns() - 2, i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
        }
    }

    public void draw() {
        for (MyButton button : buildings) {
            button.draw();
        }
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(new TerminalPosition(3, screen.getTerminalSize().getRows() - 2), "Gold: " + game.getUser().getGold(), SGR.BOLD);
        textGraphics.putString(new TerminalPosition(3, screen.getTerminalSize().getRows() - 6), "Hello " + game.getUser().getName(), SGR.BOLD);
        border();

        try {
            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
