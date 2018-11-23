package MenuPlay;

import Controls.Action;
import Controls.MyButton;
import Controls.Notification;
import MenuPlay.ElementsMenu.Gameplay;
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

public class MenuFirst {

    private Screen screen;
    private TextGraphics textGraphics;
    private List<String> textMenu = new ArrayList<String>();
    private List<MyButton> myButtons = new ArrayList<MyButton>();

    public MenuFirst(Screen screen) {
        this.screen = screen;
        this.textGraphics = this.screen.newTextGraphics();
        this.textMenu.add("New game");
        this.textMenu.add("Load game");
        this.textMenu.add("Options");
        this.textMenu.add("Exit");
    }

    public void choice() throws IOException {
        Integer activation = 0;
        while (true) {
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
                    myButtons.get(activation).go();
                    this.screen.clear();
                    this.drawTitle();
                    this.draw();
                } else if (key.getKeyType() == KeyType.Escape) {
                    Notification exitWindow = new Notification(this.screen);
                    exitWindow.setInformation("Do you want exit?");
                    exitWindow.setLeftButtonLabel("Yes");
                    exitWindow.setRightButtonLabel("No");
                    exitWindow.setActionLeftButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            screen.stopScreen();
                            System.exit(0);
                            return true;
                        }
                    });
                    exitWindow.setActionRightButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            return false;
                        }
                    });
                    exitWindow.choice();
                    this.screen.clear();
                } else if (key.getKeyType() == KeyType.EOF) {
                    Notification exitWindow = new Notification(this.screen);
                    exitWindow.setInformation("Do you want exit?");
                    exitWindow.setLeftButtonLabel("Yes");
                    exitWindow.setRightButtonLabel("No");
                    exitWindow.setActionLeftButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            screen.stopScreen();
                            System.exit(0);
                            return true;
                        }
                    });
                    exitWindow.setActionRightButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            return false;
                        }
                    });
                    exitWindow.choice();
                    this.screen.clear();
                }
            }
            this.drawTitle();
            this.draw();
        }
    }

    public void corretionPosition() {
        int x = (int) ((screen.getTerminalSize().getColumns()) * 0.05), y = (int) ((screen.getTerminalSize().getRows()) * 0.1);
        for (MyButton myButton : myButtons) {
            TerminalPosition positionButton = new TerminalPosition(x, y);
            myButton.setPosition(positionButton);
            y += myButton.getHeigth();
        }
        drawTitle();
        draw();
    }

    public void create() {
        int x = (int) ((screen.getTerminalSize().getColumns()) * 0.5) - 15, y = (int) ((screen.getTerminalSize().getRows()) * 0.45);

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                screen.clear();
                Gameplay gamefirst = new Gameplay(screen);
                gamefirst.draw();
                gamefirst.choice();
                screen.clear();
                drawTitle();
                draw();

                screen.clear();
                return false;
            }
        }));
        this.myButtons.get(0).setActive();
        this.myButtons.get(0).setLabel(textMenu.get(0));
        y += myButtons.get(0).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                screen.clear();
                Load windowLoad = new Load(screen);
                windowLoad.create();
                windowLoad.draw();
                windowLoad.choice();
                return false;
            }
        }));
        this.myButtons.get(1).setLabel(textMenu.get(1));
        y += myButtons.get(1).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {

            @Override
            public boolean run() throws IOException {
                screen.clear();
                Option option = new Option(screen);
                option.draw();
                option.choice();
                return false;
            }
        }));
        this.myButtons.get(2).setLabel(textMenu.get(2));
        y += myButtons.get(2).getHeigth();

        this.myButtons.add(new MyButton(this.textGraphics, new TerminalPosition(x, y), new Action() {
            @Override
            public boolean run() {
                try {
                    screen.stopScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                return false;
            }
        }));
        this.myButtons.get(3).setLabel(textMenu.get(3));
    }

    public void drawTitle() {
        int y = (int) ((screen.getTerminalSize().getRows()) * 0.3), c = (int) ((screen.getTerminalSize().getColumns() - 15) * 0.5);
        this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        for (int i = 3; i < 7; i++) {
            this.textGraphics.putString(new TerminalPosition(c, y - i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(c + 8, y - i), String.valueOf(Symbols.BLOCK_DENSE));
        }
        this.textGraphics.putString(new TerminalPosition(c + 1, y - 4), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(c + 1, y - 6), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(c + 5, y - 4), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(c + 5, y - 6), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(c + 9, y - 4), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(c + 9, y - 6), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(c + 13, y - 4), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(c + 13, y - 6), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(c + 10, y - 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(c + 10, y - 5), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(c + 2, y - 5), String.valueOf(Symbols.BLOCK_DENSE));

        for (int i = 3; i < 6; i++) {
            this.textGraphics.putString(new TerminalPosition(c + 4, y - i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(c + 6, y - i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(c + 12, y - i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(c + 14, y - i), String.valueOf(Symbols.BLOCK_DENSE));
        }
    }

    public void draw() {
        this.drawTitle();
        for (MyButton myButton : myButtons) {
            myButton.draw();
        }
    }

}
