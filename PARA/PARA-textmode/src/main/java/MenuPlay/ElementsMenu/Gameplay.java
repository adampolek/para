package MenuPlay.ElementsMenu;

import Controls.Action;
import Controls.EditText;
import Controls.MyButton;
import Gameplay.Game;
import Village.Village;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Gameplay {

    private EditText nameUser;
    private MyButton button;
    private Screen screen;
    private TextGraphics textGraphics;
    private int x;
    private int y;

    public Gameplay(final Screen screen) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.x = screen.getTerminalSize().getColumns() / 2;
        this.y = screen.getTerminalSize().getRows() / 2;
        this.button = new MyButton(this.textGraphics, new TerminalPosition(x - 15, y), new Action() {
            @Override
            public boolean run() throws IOException {
                Game game = new Game();
                game.createFirst();
                game.loadMaps();
                if (nameUser.getEdittext().isEmpty()) {
                    game.getUser().setName("Stranger");
                } else {
                    game.getUser().setName(nameUser.getEdittext());
                }
                screen.clear();
                Village village = new Village(screen);
                village.setGame(game);
                village.draw();
                village.choice();
                return false;
            }
        });
        this.button.setLabel("Create");
        this.nameUser = new EditText(screen, x - 19, y - 3);
        this.nameUser.setLabel("Name");
        this.nameUser.setActive();
    }

    public void choice() {
        boolean run = true;
        this.draw();

        while (run) {
            try {
                this.screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
            KeyStroke key = null;
            try {
                key = this.screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowUp ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W') ||
                        key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    this.nameUser.setActive();
                    this.button.setActive();
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (this.nameUser.getActive()) {
                        try {
                            run = this.nameUser.go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (this.button.getActive()) {
                        try {
                            run = this.button.go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.getKeyType() == KeyType.Escape) {
                    run = false;
                } else if (key.getKeyType() == KeyType.EOF) {
                    try {
                        screen.stopScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            }
        }
    }

    public void drawTitle() {
        int c = (int) ((screen.getTerminalSize().getRows() / 2) * 0.35);

        for (int i = c; i < c + 4; i++) {
            this.textGraphics.putString(new TerminalPosition(x - 17, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x - 14, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        for (int i = c - 1; i < c + 4; i++) {
            this.textGraphics.putString(new TerminalPosition(x - 12, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 8, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 12, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 14, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        this.textGraphics.putString(new TerminalPosition(x - 16, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 15, c + 2), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x - 11, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 10, c - 1), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x - 11, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 10, c + 1), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x - 11, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 10, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x - 8, c), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 8, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 8, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 7, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 6, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 5, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 4, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 4, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x - 4, c), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 1, c), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 1, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 2, c), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 2, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 2, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 4, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 4, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 4, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 5, c), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 5, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 6, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 6, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 6, c + 2), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 9, c), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 10, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 11, c), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 15, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 16, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 15, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 16, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 15, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 16, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
    }


    public void draw() {
        this.drawTitle();
        this.button.draw();
        this.nameUser.draw();

        for (int i = this.x - 19; i < this.x + 20; i++) {
            this.textGraphics.putString(new TerminalPosition(i, y - 4), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
            this.textGraphics.putString(new TerminalPosition(i, y + 3), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
        }

        for (int i = this.y - 3; i < this.y + 3; i++) {
            this.textGraphics.putString(new TerminalPosition(x - 20, i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
            this.textGraphics.putString(new TerminalPosition(x + 20, i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
        }

        this.textGraphics.putString(new TerminalPosition(x - 20, y - 4), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(x + 20, y - 4), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER));
        this.textGraphics.putString(new TerminalPosition(x - 20, y + 3), String.valueOf(Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(x + 20, y + 3), String.valueOf(Symbols.DOUBLE_LINE_TOP_LEFT_CORNER));

    }

}
