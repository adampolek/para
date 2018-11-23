package Mission;

import Characters.Character;
import Controls.AttackButton;
import Controls.MyButton;
import Gameplay.Game;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FightTextMode {

    private Screen screen;
    private TextGraphics textGraphics;
    private Game game;
    private TerminalPosition start;
    private int selectQueue = 0;
    private List<MyButton> enemies = new ArrayList<>();
    private List<String> hpEnemies = new ArrayList<>();
    private List<AttackButton> heroes = new ArrayList<>();
    private List<String> hpHeroes = new ArrayList<>();

    public FightTextMode(Screen screen, Game game) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.game = game;
        this.start = new TerminalPosition((int) (screen.getTerminalSize().getColumns() * 0.5) + 2, 0);
        create();
    }

    public void create() {
        game.randomEnemies();
        int x = (int) (((screen.getTerminalSize().getColumns() - start.getColumn()) - game.getEnemies().size() * 30) * 0.5) + start.getColumn();

        for (Character character : game.getEnemies()) {
            MyButton button = new MyButton(textGraphics, new TerminalPosition(x, 5));
            button.setLabel(character.getName());
            enemies.add(button);
            x += 30;
        }
        int y = screen.getTerminalSize().getRows();
        x = (int) (((screen.getTerminalSize().getColumns() - start.getColumn()) - game.getCharacterInMission().size() * 30) * 0.5) + start.getColumn();
        for (Character character : game.getCharacterInMission()) {
            AttackButton attackButton = new AttackButton(textGraphics);
            MyButton button = new MyButton(textGraphics, new TerminalPosition(x, y - (2 + 13)));
            button.setLabel(character.getName());
            attackButton.setHero(button);
            MyButton[] attacks = new MyButton[3];
            attacks[0] = new MyButton(textGraphics, new TerminalPosition(x, y - (2 + 9)));
            attacks[1] = new MyButton(textGraphics, new TerminalPosition(x, y - (2 + 6)));
            attacks[2] = new MyButton(textGraphics, new TerminalPosition(x, y - (2 + 3)));
            if (character.getType() == "Wizard") {
                attacks[0].setLabel("Attack");
                attacks[1].setLabel("Healing");
                attacks[2].setLabel("Random Healing");
            } else {
                attacks[0].setLabel("Attack 1");
                attacks[1].setLabel("Area Attack");
                attacks[2].setLabel("Attack 2");
            }
            attackButton.setAttacks(attacks);
            heroes.add(attackButton);
            x += 30;
        }
    }


    public boolean choice() {
        boolean run = true;
        int end = game.getQueue().size() - 1;

        while (run) {
            hpEnemies.clear();
            hpHeroes.clear();

            for (Character character : game.getCharacterInMission()) {
                hpHeroes.add(character.getHp() + " Hp");
            }

            for (Character character : game.getEnemies()) {
                hpEnemies.add(character.getHp() + " Hp");
            }

            if (game.getEnemies().contains(game.getQueue().get(selectQueue))) {
                for (MyButton button : enemies) {
                    if (button.getLabel().equals(game.getQueue().get(selectQueue).getName())) {

                        button.setActive();

                        clearWindow();
                        draw();
                        try {
                            screen.refresh();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Character character = game.enemyRandomAttack(game.getQueue().get(selectQueue));
                        if (character.getHp() < 0) {
                            game.deletehp(game.getCastle().getCharacters().indexOf(character));
                            game.deadHero(character);
                            for (AttackButton attackButton : heroes) {
                                if (attackButton.getHero().getLabel().equals(character.getName())) {
                                    heroes.remove(attackButton);
                                    break;
                                }
                            }
                        }
                        button.setActive();
                        selectQueue++;
                        end = game.getQueue().size() - 1;
                        if (end < selectQueue) {
                            selectQueue = 0;
                        }
                        break;
                    }
                }
            } else {
                for (AttackButton button : heroes) {
                    if (button.getHero().getLabel().equals(game.getQueue().get(selectQueue).getName())) {
                        button.getHero().setActive();
                        clearWindow();
                        draw();
                        try {
                            screen.refresh();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        button.getAttacks()[0].setActive();

                        int activation = 0;

                        while (run) {
                            clearWindow();
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
                                if (key.getKeyType() == KeyType.ArrowUp ||
                                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w') ||
                                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'W')) {
                                    button.getAttacks()[activation].setActive();
                                    if (activation != 0) {
                                        activation--;
                                    }
                                    button.getAttacks()[activation].setActive();
                                } else if (key.getKeyType() == KeyType.ArrowDown ||
                                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 's') ||
                                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'S')) {
                                    button.getAttacks()[activation].setActive();
                                    if (activation != 2) {
                                        activation++;
                                    }
                                    button.getAttacks()[activation].setActive();
                                } else if (key.getKeyType() == KeyType.Enter) {
                                    if (game.getQueue().get(selectQueue).getType() == "Wizard") {
                                        if (activation == 0) {
                                            choiceTarget(game.getQueue().get(selectQueue), activation);
                                        } else {
                                            game.attackWizard(game.getQueue().get(selectQueue), activation);
                                        }
                                    } else {
                                        if (activation == 1) {
                                            game.attackHero(game.getQueue().get(selectQueue), game.getEnemies().get(0), 1);
                                        } else {
                                            choiceTarget(game.getQueue().get(selectQueue), activation);
                                        }
                                    }
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

                        button.getAttacks()[activation].setActive();
                        button.getHero().setActive();
                        selectQueue++;
                        end = game.getQueue().size() - 1;
                        if (end < selectQueue) {
                            selectQueue = 0;
                        }
                        break;
                    }
                }
            }
            run = true;
            if (enemies.isEmpty()) {
                run = false;
            }
            if (heroes.isEmpty()) {
                screen.clear();
                return false;
            }
        }
        return true;
    }

    public void choiceTarget(Character character, int selectAttack) {
        boolean run = true;
        int activation = 0;
        enemies.get(activation).setActive();

        while (run) {
            clearWindow();
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
                    enemies.get(activation).setActive();
                    if (activation != 0) {
                        activation--;
                    }
                    enemies.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    enemies.get(activation).setActive();
                    if (activation != enemies.size() - 1) {
                        activation++;
                    }
                    enemies.get(activation).setActive();
                } else if (key.getKeyType() == KeyType.Enter) {
                    game.attackHero(character, game.getEnemies().get(activation), selectAttack);
                    enemies.get(activation).setActive();
                    if (game.getEnemies().get(activation).getHp() <= 0) {
                        game.deadEnemy(game.getEnemies().get(activation));
                        enemies.remove(activation);
                    }
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

    public void clearWindow() {
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        for (int i = (int) (screen.getTerminalSize().getColumns() * 0.5) + 2; i <= screen.getTerminalSize().getColumns(); i++) {
            for (int j = 0; j < screen.getTerminalSize().getRows(); j++) {
                textGraphics.putString(new TerminalPosition(i, j), " ");
            }
        }
    }

    public void draw() {
        int i = 0;
        for (MyButton button : enemies) {
            textGraphics.putString(new TerminalPosition(button.getPosition().getColumn() + 12, 2), hpEnemies.get(i));
            button.draw();
            i++;
        }
        i = 0;
        for (AttackButton attackButton : heroes) {
            textGraphics.putString(new TerminalPosition(attackButton.getHero().getPosition().getColumn() + 12, screen.getTerminalSize().getRows() - 1), hpHeroes.get(i));
            attackButton.draw();
            i++;
        }
    }

}
