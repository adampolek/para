package MenuPlay.ElementsMenu;

import Controls.Action;
import Controls.MyButton;
import Controls.Notification;
import Gameplay.Game;
import Village.Village;
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

public class Load {
    private Screen screen;
    private TextGraphics textGraphics;
    private List<MyButton> list = new ArrayList<MyButton>();
    private List<String> name = new ArrayList<String>();
    private Game game = null;
    private boolean saveORLoad = false;

    public Load(Screen screen) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.game = new Game();
        this.game.loadMaps();
    }

    public Load(Screen screen, Game game) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.saveORLoad = true;
        this.game = game;
    }

    public void choice() {
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
                    this.list.get(activation).setActive();
                    if (activation != 0) {
                        activation--;
                    }
                    this.list.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    this.list.get(activation).setActive();
                    if (activation != this.list.size() - 1) {
                        activation++;
                    }
                    this.list.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.Escape) {
                    Notification exitWindow = new Notification(this.screen);
                    exitWindow.setInformation("Are you sure you want to go back?");
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
                            screen.clear();
                            return false;
                        }
                    });
                    try {
                        run = exitWindow.choice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (saveORLoad) {
                        Notification exitWindow = new Notification(this.screen);
                        exitWindow.setInformation("Do you want to save?");
                        exitWindow.setLeftButtonLabel("Yes");
                        exitWindow.setRightButtonLabel("No");
                        final Integer finalActivation = activation;
                        exitWindow.setActionLeftButton(new Action() {
                            @Override
                            public boolean run() throws IOException {
                                game.getUser().saveToFile("save" + finalActivation + ".txt");
                                screen.clear();
                                textGraphics.putString(new TerminalPosition(screen.getTerminalSize().getColumns() / 2 - 10, screen.getTerminalSize().getRows() - 2), "Game saved!");
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
                            exitWindow.choice();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        boolean exist = false;
                        try {
                            exist = game.getUser().loadFromFile("save" + activation + ".txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (exist) {
                            screen.clear();
                            Village village = new Village(screen);
                            village.setGame(game);
                            village.draw();
                            run = village.choice();
                        } else {
                            this.textGraphics.putString(new TerminalPosition(screen.getTerminalSize().getColumns() / 2 - 26, screen.getTerminalSize().getRows() - 2), "Oops! There was an unexpected error, we are very sorry!");
                        }
                    }
                } else if (key.getKeyType() == KeyType.EOF) {
                    try {
                        this.screen.stopScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                this.draw();
            }
        }
    }

    public void create() {
        int x = (int) (((screen.getTerminalSize().getColumns()) - 34) * 0.5), y = (int) ((screen.getTerminalSize().getRows()) * 0.4);

        for (int i = 0; i < 5; i++) {
            MyButton button = new MyButton(this.textGraphics, new TerminalPosition(x, y));
            button.setAction(new Action() {
                @Override
                public boolean run() throws IOException {
                    return false;
                }
            });
            y += 3;
            button.setLabel("Save " + (i + 1));
            this.list.add(button);
        }
        this.list.get(0).setActive();
    }

    public void drawTitle() {
        int x = screen.getTerminalSize().getColumns() / 2 - 2, c = (int) ((screen.getTerminalSize().getRows() / 2) * 0.35);
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

        if (saveORLoad) {
            textGraphics.putString(new TerminalPosition(x - 7, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 7, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 6, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 6, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 6, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 5, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 5, c + 2), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x - 3, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 3, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 3, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 2, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 2, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 1, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 1, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 1, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x + 1, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 1, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 1, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 2, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x + 5, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 6, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 6, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 6, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 7, c - 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 7, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 7, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
        } else {
            textGraphics.putString(new TerminalPosition(x - 7, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 7, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 7, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 7, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 6, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 5, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x - 3, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 3, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 2, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 2, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 1, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x - 1, c + 1), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x + 1, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 1, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 1, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 2, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 2, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 3, c + 3), String.valueOf(Symbols.BLOCK_DENSE));

            textGraphics.putString(new TerminalPosition(x + 5, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 5, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 6, c + 3), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 6, c), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 7, c + 1), String.valueOf(Symbols.BLOCK_DENSE));
            textGraphics.putString(new TerminalPosition(x + 7, c + 2), String.valueOf(Symbols.BLOCK_DENSE));
        }
    }

    public void draw() {
        drawTitle();
        for (MyButton button : list) {
            button.draw();
        }
        try {
            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
