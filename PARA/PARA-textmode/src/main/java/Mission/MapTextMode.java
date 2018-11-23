package Mission;

import Controls.Action;
import Controls.Notification;
import Gameplay.Game;
import MenuPlay.GameMenu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MapTextMode {

    private Screen screen;
    private TextGraphics textGraphics;
    private Game game;

    public MapTextMode(Screen screen, Game game) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.game = game;
    }

    public boolean choice() {
        boolean run = true;

        while (run) {
            draw();
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.putString(new TerminalPosition(game.getHeroControll().getH().getY() + 30, game.getHeroControll().getH().getX() + 20), String.valueOf(Symbols.BLOCK_SOLID));
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
            KeyStroke key = null;
            try {
                key = screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(new TerminalPosition(game.getHeroControll().getH().getY() + 30, game.getHeroControll().getH().getX() + 20), String.valueOf(Symbols.BLOCK_SOLID));
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowLeft ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'A')) {
                    game.getHeroControll().goLeft();
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    game.getHeroControll().goRight();
                } else if (key.getKeyType() == KeyType.ArrowUp ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                    game.getHeroControll().goUp();
                } else if (key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    game.getHeroControll().goDown();
                } else if (key.getKeyType() == KeyType.Escape) {
                    GameMenu gamemenu = new GameMenu(screen, game);
                    gamemenu.create();
                    gamemenu.getMyButtons().get(3).setLabel("Village");
                    gamemenu.draw();
                    try {
                        run = gamemenu.choice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (key.getKeyType() == KeyType.EOF) {
                    try {
                        screen.stopScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            }
            if (game.getHeroControll().exit()) {
                Notification exitWindow = new Notification(this.screen);
                exitWindow.setInformation("Do you want to return to village??");
                exitWindow.setLeftButtonLabel("Yes");
                exitWindow.setRightButtonLabel("No");
                exitWindow.setActionLeftButton(new Action() {
                    @Override
                    public boolean run() throws IOException {
                        game.backFromMission();
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
                    run = exitWindow.choice();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.screen.clear();
            }
            if (game.getHeroControll().startFight()) {
                clearWindow();

                FightTextMode fightTextMode = new FightTextMode(screen, game);
                run = fightTextMode.choice();

                game.changeCellMap();
                game.getEnemies().clear();
                game.getQueue().clear();
                clearWindow();
            }
        }
        return false;
    }

    public void clearWindow() {
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        for (int i = (int) (screen.getTerminalSize().getColumns() * 0.5) + 2; i <= screen.getTerminalSize().getColumns(); i++) {
            for (int j = 0; j < screen.getTerminalSize().getRows(); j++) {
                textGraphics.putString(new TerminalPosition(i, j), " ");
            }
        }
    }

    public void draw() {
        for (int z = 0; z < 25; z++) {
            for (int i = 0; i < 25; i++) {
                textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                switch (game.getHeroControll().getH().getVarible(z, i)) {
                    case 0:
                        break;
                    case 1:
                        textGraphics.putString(new TerminalPosition(i + 30, z + 20), String.valueOf(Symbols.BLOCK_SOLID));
                        break;
                    case 2:
                        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                        textGraphics.putString(new TerminalPosition(i + 30, z + 20), String.valueOf(Symbols.BLOCK_SOLID));
                        break;
                    case 3:
                        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
                        textGraphics.putString(new TerminalPosition(i + 30, z + 20), String.valueOf(Symbols.BLOCK_SOLID));
                        break;
                    default:
                        break;
                }
            }
        }

        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

        textGraphics.drawLine((int) (screen.getTerminalSize().getColumns() * 0.5) - 1, 0, (int) (screen.getTerminalSize().getColumns() * 0.5) - 1, screen.getTerminalSize().getRows(), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.drawLine((int) (screen.getTerminalSize().getColumns() * 0.5), 0, (int) (screen.getTerminalSize().getColumns() * 0.5), screen.getTerminalSize().getRows(), Symbols.WHITE_CIRCLE);
        textGraphics.drawLine((int) (screen.getTerminalSize().getColumns() * 0.5) + 1, 0, (int) (screen.getTerminalSize().getColumns() * 0.5) + 1, screen.getTerminalSize().getRows(), Symbols.DOUBLE_LINE_VERTICAL);

        textGraphics.putString(new TerminalPosition((int) (screen.getTerminalSize().getColumns() * 0.5) + 3, (int) (screen.getTerminalSize().getRows() * 0.5) - 2), game.getUser().getName() + ",", SGR.BOLD);
        textGraphics.putString(new TerminalPosition((int) (screen.getTerminalSize().getColumns() * 0.5) + 3, (int) (screen.getTerminalSize().getRows() * 0.5)), "The corridors are surprisingly calm.", SGR.ITALIC);
        textGraphics.putString(new TerminalPosition((int) (screen.getTerminalSize().getColumns() * 0.5) + 3, (int) (screen.getTerminalSize().getRows() * 0.5) + 1), "Stay aware, for you not know what might be lurking in there.", SGR.ITALIC);

    }

}
