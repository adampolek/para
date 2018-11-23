package Village.VillageElement;

import Characters.Character;
import Controls.Action;
import Controls.MyButton;
import Controls.Notification;
import Gameplay.Game;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private Screen screen;
    private TextGraphics textGraphics;
    private TerminalPosition start;
    private TerminalPosition end;
    private List<MyButton> elements = new ArrayList<MyButton>();
    private Game game;

    public Equipment(Screen screen, TerminalPosition start, TerminalPosition end, Game game) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.start = start;
        this.end = end;
        this.game = game;
        create();

    }

    public void create() {
        elements.clear();
        int x = start.getColumn(), y = start.getRow() + 17;
        for (final Character character : game.getUser().getUsersCharacters()) {
            MyButton button = new MyButton(textGraphics, new TerminalPosition(x, y), new Action() {
                @Override
                public boolean run() throws IOException {
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                    for (int i = start.getColumn(); i < end.getColumn(); i++) {
                        for (int z = start.getRow(); z < start.getRow() + 14; z++) {
                            textGraphics.putString(new TerminalPosition(i, z), " ");
                        }
                    }
                    String nameClass = String.valueOf(character.getClass());
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 2), nameClass.substring(17, nameClass.length()), SGR.BOLD);
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 4), "Health: " + character.getHp() + "( Next level: " + character.getHp_level() * 10 + " gold)");
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 6), "Attack: " + character.getAttack() + "( Next level: " + character.getAttack_level() * 10 + " gold)");
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 8), "Defence: " + character.getDefence() + "( Next level: " + character.getDefence_level() * 10 + " gold)");
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 10), "Speed: " + character.getSpeed() + "( Next level: " + character.getSpeed_level() * 10 + " gold)");
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 12), "Dodge: " + character.getDodge() + "( Next level: " + character.getDodge_level() * 10 + " gold)");
                    textGraphics.putString(new TerminalPosition(start.getColumn() + 2, start.getRow() + 14), "Crit chance: " + character.getCrit_chance() + "( Next level: " + character.getCrit_chance_level() * 10 + " gold)");
                    return true;
                }
            });
            button.setLabel(character.getName());
            elements.add(button);
            if (elements.size() % 7 == 0 && elements.size() > 2) {
                x = start.getColumn();
                y += button.getHeigth();
            } else {
                x += button.getWidth();
            }
        }
        elements.get(0).setActive();
        try {
            elements.get(0).go();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choice() {
        boolean run = true;
        int activation = 0;

        while (run) {
            draw();
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
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowLeft ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'A')) {
                    if (activation != 0) {
                        elements.get(activation).setActive();
                        activation--;
                        elements.get(activation).setActive();
                        try {
                            elements.get(activation).go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    if (activation < elements.size() - 1) {
                        elements.get(activation).setActive();
                        activation++;
                        elements.get(activation).setActive();
                        try {
                            elements.get(activation).go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.getKeyType() == KeyType.ArrowUp ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                    if (activation >= 7) {
                        elements.get(activation).setActive();
                        activation -= 7;
                        elements.get(activation).setActive();
                        try {
                            elements.get(activation).go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    if (activation + 7 < elements.size() - 1) {
                        elements.get(activation).setActive();
                        activation += 7;
                        elements.get(activation).setActive();
                        try {
                            elements.get(activation).go();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    Notification removeWindow = new Notification(this.screen);
                    removeWindow.setInformation("Do you want to remove " + elements.get(activation).getLabel() + "?");
                    removeWindow.setLeftButtonLabel("Yes");
                    removeWindow.setRightButtonLabel("No");
                    final int finalActivation = activation;
                    removeWindow.setActionLeftButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            game.getUser().deleteCharacter(game.getUser().getUsersCharacters().get(finalActivation));
                            elements.remove(finalActivation);
                            return false;
                        }
                    });
                    removeWindow.setActionRightButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            return false;
                        }
                    });
                    try {
                        removeWindow.choice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!elements.isEmpty()) {
                        activation = 0;
                        create();
                    } else {
                        run = false;
                    }
                    clearWindow();
                } else if (key.getKeyType() == KeyType.Escape) {
                    run = false;
                    clearWindow();
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

    public void clearWindow() {
        for (int i = start.getColumn(); i < end.getColumn(); i++) {
            for (int z = start.getRow(); z < end.getRow(); z++) {
                textGraphics.putString(new TerminalPosition(i, z), " ");
            }
        }
    }

    public void draw() {
        for (MyButton button : elements) {
            button.draw();
        }
    }
}

