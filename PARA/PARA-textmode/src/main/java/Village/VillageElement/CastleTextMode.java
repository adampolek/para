package Village.VillageElement;

import Characters.Character;
import Controls.Action;
import Controls.MyButton;
import Gameplay.Game;
import Maps.Map;
import Mission.MapTextMode;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CastleTextMode {

    private Screen screen;
    private TextGraphics textGraphics;
    private TerminalPosition start;
    private TerminalPosition end;
    private List<MyButton> map = new ArrayList<MyButton>();
    private List<MyButton> characters = new ArrayList<MyButton>();
    private List<MyButton> selectedCharacters = new ArrayList<MyButton>();
    private List<Integer> chosen = new ArrayList<Integer>();
    private Game game;
    private boolean backToVilage = false;

    public CastleTextMode(Screen screen, TerminalPosition start, TerminalPosition end, Game game) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.start = start;
        this.end = end;
        this.game = game;
        create();
    }

    public void create() {
        int x = start.getColumn() + (int) ((end.getColumn() - start.getColumn() - 90) * 0.5), y = start.getRow() + 3;
        for (Map map : game.getCastle().getMaps()) {
            MyButton button = new MyButton(textGraphics, new TerminalPosition(x, y));
            button.setLabel(map.getName());
            x += 30;
            this.map.add(button);
        }
        this.map.get(0).setActive();

        x = start.getColumn() + (int) ((end.getColumn() - start.getColumn() - 90) * 0.5);

        for (int i = x - 15; i < x + 55; i += 30) {
            MyButton button = new MyButton(textGraphics, new TerminalPosition(i, y + 6));
            button.setLabel("Empty");
            this.selectedCharacters.add(button);
        }
        this.selectedCharacters.add(new MyButton(textGraphics, new TerminalPosition(x + 90, y + 6)));
        this.selectedCharacters.get(3).setLabel("GO");

        x = start.getColumn();
        y = start.getRow() + 15;
        for (Character character : game.getUser().getUsersCharacters()) {
            MyButton button = new MyButton(textGraphics, new TerminalPosition(x, y), new Action() {
                @Override
                public boolean run() throws IOException {
                    return true;
                }
            });
            button.setLabel(character.getName());
            characters.add(button);
            if (characters.size() % 7 == 0 && characters.size() > 2) {
                x = start.getColumn();
                y += button.getHeigth();
            } else {
                x += button.getWidth();
            }
        }
    }

    public void choiceMap() {
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
                        map.get(activation).setActive();
                        activation--;
                        map.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    if (activation < 2) {
                        map.get(activation).setActive();
                        activation++;
                        map.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    run = choicePlace(activation);
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

    public boolean choicePlace(int selectMap) {
        boolean run = true;
        int activation = 0;
        selectedCharacters.get(activation).setActive();

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
                        selectedCharacters.get(activation).setActive();
                        activation--;
                        selectedCharacters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    if (activation < 3) {
                        selectedCharacters.get(activation).setActive();
                        activation++;
                        selectedCharacters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (activation != 3) {
                        choiceCharacter(activation);
                    } else if (game.getCastle().getCharacters().size() > 0) {
                        screen.clear();
                        game.goMission(game.getCastle().selectMap(selectMap));
                        MapTextMode mapTextMode = new MapTextMode(screen, game);
                        run = mapTextMode.choice();
                        game.backFromMission();
                        backToVilage = true;
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
        selectedCharacters.get(activation).setActive();
        if (backToVilage) {
            return false;
        }
        return true;
    }

    public void choiceCharacter(int selected) {
        boolean run = true;
        int activation = 0;
        characters.get(activation).setActive();

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
                        characters.get(activation).setActive();
                        activation--;
                        characters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    if (activation < characters.size() - 1) {
                        characters.get(activation).setActive();
                        activation++;
                        characters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowUp ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                    if (activation >= 7) {
                        characters.get(activation).setActive();
                        activation -= 7;
                        characters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.ArrowDown ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                    if (activation + 7 < characters.size() - 1) {
                        characters.get(activation).setActive();
                        activation += 7;
                        characters.get(activation).setActive();
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    boolean isList = false;
                    for (Integer i : chosen) {
                        if (i.equals(activation)) {
                            isList = true;
                            break;
                        }
                    }
                    if (!isList) {
                        if (chosen.size() == 3) {
                            game.getCastle().removeCharacters(game.getUser().getUsersCharacters().get(chosen.get(selected)));
                            chosen.remove(selected);
                        }
                        game.getCastle().addCharcters(game.getUser().getUsersCharacters().get(activation));
                        chosen.add(activation);
                        int i = 0;
                        for (Integer ch : chosen) {
                            selectedCharacters.get(i).setLabel(game.getUser().getUsersCharacters().get(ch).getName());
                            i++;
                        }
                    }
                    run = false;
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
        characters.get(activation).setActive();
    }

    public void draw() {
        for (MyButton button : map) {
            button.draw();
        }
        for (MyButton button : selectedCharacters) {
            button.draw();
        }
        for (MyButton button : characters) {
            button.draw();
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
