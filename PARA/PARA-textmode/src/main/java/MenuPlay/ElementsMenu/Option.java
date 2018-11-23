package MenuPlay.ElementsMenu;

import Controls.Action;
import Controls.Notification;
import Controls.Strip;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Option {

    private Screen screen;
    private TextGraphics textGraphics;
    private Strip volume;

    public void saveToFile() throws IOException {
        File file = new File("options.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(volume.getSelect()));
        writer.close();
    }

    public int loadFromFile() throws FileNotFoundException {
        File file = new File("options.txt");
        if (!file.exists())
            return 0;
        Scanner scanner = new Scanner(file);
        return scanner.nextInt();
    }

    public Option(Screen screen) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        try {
            this.volume = new Strip(this.textGraphics, 10, 10, loadFromFile(), new Action() {
                @Override
                public boolean run() throws IOException {
                    Music.setLevel((volume.getSelect()) * 0.1);
                    return false;
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.volume.setLabel("Volume");
        this.volume.setActive();
    }

    public void choice() throws IOException {
        boolean run = true;

        int counter = 1;
        while (run) {
            this.screen.refresh();
            KeyStroke key = this.screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowLeft ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'A')) {
                    if (this.volume.getSelect() != 0)
                        this.volume.setSelect(this.volume.getSelect() - 1);
                } else if (key.getKeyType() == KeyType.ArrowRight ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') ||
                        (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    if (this.volume.getSelect() != 10)
                        this.volume.setSelect(this.volume.getSelect() + 1);
                } else if (key.getKeyType() == KeyType.EOF) {
                    screen.stopScreen();
                    System.exit(0);
                } else if (key.getKeyType() == KeyType.Enter) {

                } else if (key.getKeyType() == KeyType.Escape) {
                    Notification exitWindow = new Notification(this.screen);
                    exitWindow.setInformation("Save change?");
                    exitWindow.setLeftButtonLabel("Yes");
                    exitWindow.setRightButtonLabel("No");
                    exitWindow.setActionLeftButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            saveToFile();
                            return false;
                        }
                    });
                    exitWindow.setActionRightButton(new Action() {
                        @Override
                        public boolean run() throws IOException {
                            Music.setLevel(loadFromFile() * 0.1);
                            return false;
                        }
                    });
                    exitWindow.choice();
                    run = false;
                }
            }
        }
    }

    public void drawTitle() {
        int x = (int) ((this.screen.getTerminalSize().getColumns() - 26) * 0.5), y = (int) ((screen.getTerminalSize().getRows()) * 0.1);
        this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        for (int i = y; i < y + 4; i++) {
            this.textGraphics.putString(new TerminalPosition(x + 4, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 9, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 12, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 18, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 21, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        for (int i = y + 1; i < y + 3; i++) {
            this.textGraphics.putString(new TerminalPosition(x, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 2, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 14, i), String.valueOf(Symbols.BLOCK_DENSE));
            this.textGraphics.putString(new TerminalPosition(x + 16, i), String.valueOf(Symbols.BLOCK_DENSE));
        }

        this.textGraphics.putString(new TerminalPosition(x + 24, y - 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 25, y - 1), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 1, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 5, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 8, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 10, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 15, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 15, y), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 23, y), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 6, y + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 19, y + 1), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 24, y + 1), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 5, y + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 20, y + 2), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 25, y + 2), String.valueOf(Symbols.BLOCK_DENSE));

        this.textGraphics.putString(new TerminalPosition(x + 1, y + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 15, y + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 23, y + 3), String.valueOf(Symbols.BLOCK_DENSE));
        this.textGraphics.putString(new TerminalPosition(x + 24, y + 3), String.valueOf(Symbols.BLOCK_DENSE));

    }

    public void draw() {
        int x = (int) ((this.screen.getTerminalSize().getColumns() - 26) * 0.5), y = (int) ((screen.getTerminalSize().getRows()) * 0.1);
        this.drawTitle();
        this.volume.draw();
        this.textGraphics.putString(new TerminalPosition(10, 14), "Control: ");
        this.textGraphics.putString(new TerminalPosition(10, 16), "Move with arrows");
        this.textGraphics.putString(new TerminalPosition(10, 18), "Press Enter to select chosen option");
        this.textGraphics.putString(new TerminalPosition(10, 20), "Press Esc to go back");
        this.textGraphics.putString(new TerminalPosition(10, 22), "Good luck! :)");
    }


}
