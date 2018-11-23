package MenuPlay;

import Controls.Action;
import Controls.MyButton;
import Controls.Notification;
import Gameplay.Game;
import MenuPlay.ElementsMenu.Load;
import MenuPlay.ElementsMenu.Option;
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

public class GameMenu {

    private Screen screen;
    private TextGraphics textGraphics;
    private List<MyButton> myButtons = new ArrayList<MyButton>();
    private Game game;

    public List<MyButton> getMyButtons() {
        return myButtons;
    }

    public GameMenu(Screen screen, Game game) {
        this.screen = screen;
        this.textGraphics = this.screen.newTextGraphics();
        this.game = game;
    }

    public boolean choice() throws IOException {
        Integer activation = 0;
        boolean make = true;
        screen.clear();
        boolean run = true;
        while (run) {
            this.drawTitle();
            this.draw();
            this.screen.refresh();

            KeyStroke key = screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowUp || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                    if (!activation.equals(0)) {
                        myButtons.get(activation).setActive();
                        activation--;
                        myButtons.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowDown || (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    if (!activation.equals(3)) {
                        myButtons.get(activation).setActive();
                        activation++;
                        myButtons.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (activation == 0) {
                        run = myButtons.get(activation).go();
                    } else if (activation == 3) {
                        make = myButtons.get(activation).go();
                        run = make;
                    } else
                        make = myButtons.get(activation).go();
                    this.screen.clear();
                } else if (key.getKeyType() == KeyType.Escape) {
                    run = false;
                } else if (key.getKeyType() == KeyType.EOF) {
                    screen.stopScreen();
                    System.exit(0);
                }
            }
        }
        screen.clear();
        return make;
    }

    public void create() {
        int x = (int) ((screen.getTerminalSize().getColumns()) * 0.5) - 15, y = (int) ((screen.getTerminalSize().getRows()) * 0.45);

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                return false;
            }
        }));
        this.myButtons.get(0).setActive();
        this.myButtons.get(0).setLabel("Resume");
        y += myButtons.get(0).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                screen.clear();
                Load windowLoad = new Load(screen, game);
                windowLoad.create();
                windowLoad.draw();
                windowLoad.choice();
                return true;
            }
        }));
        this.myButtons.get(1).setLabel("Save");
        y += myButtons.get(1).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {

            @Override
            public boolean run() throws IOException {
                screen.clear();
                Option option = new Option(screen);
                option.draw();
                option.choice();
                return true;
            }
        }));
        this.myButtons.get(2).setLabel("Options");
        y += myButtons.get(2).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                Notification exitWindow = new Notification(screen);
                exitWindow.setInformation("Do you want exit?");
                exitWindow.setLeftButtonLabel("Yes");
                exitWindow.setRightButtonLabel("No");
                exitWindow.setActionLeftButton(new Action() {
                    @Override
                    public boolean run() throws IOException {
                        return false;
                    }
                });
                exitWindow.setActionRightButton(new Action() {
                    @Override
                    public boolean run() throws IOException {
                        return false;
                    }
                });
                try {
                    return exitWindow.choice();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }));
        this.myButtons.get(3).setLabel("Quit game");
    }

    public void drawTitle() {
        int x = screen.getTerminalSize().getColumns() / 2, c = (int) ((screen.getTerminalSize().getRows() / 2) * 0.5);
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

        for (int i = c - 1; i < c + 4; i++) {
            textGraphics.putString(new TerminalPosition(x - 9, i), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 5, i), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 3, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        for (int i = c; i < c + 4; i++) {
            textGraphics.putString(new TerminalPosition(x + 1, i), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 4, i), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 8, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        textGraphics.putString(new TerminalPosition(x - 8, c), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 7, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 6, c), String.valueOf(Symbols.BLOCK_DENSE));

        textGraphics.putString(new TerminalPosition(x - 2, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 1, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 2, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 1, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 2, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x - 1, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

        textGraphics.putString(new TerminalPosition(x + 2, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x + 3, c + 2), String.valueOf(Symbols.BLOCK_DENSE));

        textGraphics.putString(new TerminalPosition(x + 6, c), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x + 6, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x + 6, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        textGraphics.putString(new TerminalPosition(x + 7, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

    }

    public void draw() {
        //this.drawTitle();
        for (MyButton myButton : myButtons) {
            myButton.draw();
        }
    }
}
