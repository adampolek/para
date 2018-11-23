package Controls;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Notification {
    private String information = "Empty";
    private MyButton leftButton;
    private MyButton rightButton;
    private Screen screen;
    private TextGraphics textGraphics;
    private int x;
    private int y;

    public Notification(Screen screen) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.x = screen.getTerminalSize().getColumns() / 2;
        this.y = screen.getTerminalSize().getRows() / 2;
        this.leftButton = new MyButton(this.textGraphics, new TerminalPosition(x - 31, y));
        this.leftButton.setLabel("OK");
        this.leftButton.setActive();
        this.rightButton = new MyButton(this.textGraphics, new TerminalPosition(x, y));
        this.rightButton.setLabel("Cancel");
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setLeftButtonLabel(String title) {
        this.leftButton.setLabel(title);
    }

    public void setRightButtonLabel(String title) {
        this.rightButton.setLabel(title);
    }

    public void setActionLeftButton(Action action) {
        this.leftButton.setAction(action);
    }

    public void setActionRightButton(Action action) {
        this.rightButton.setAction(action);
    }

    public boolean choice() throws IOException {
        boolean make = true;
        boolean run = true;
        this.draw();

        while (run) {
            this.screen.refresh();
            KeyStroke key = this.screen.readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.ArrowLeft || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'A') || key.getKeyType() == KeyType.ArrowRight || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd') || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'D')) {
                    this.leftButton.setActive();
                    this.rightButton.setActive();
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (this.leftButton.getActive()) {
                        run = this.leftButton.go();
                        make = false;
                    }
                    if (this.rightButton.getActive()) {
                        run = this.rightButton.go();
                        make = true;
                    }
                } else if (key.getKeyType() == KeyType.Escape) {
                    run = this.rightButton.go();
                } else if (key.getKeyType() == KeyType.EOF) {
                    screen.stopScreen();
                    System.exit(0);
                }
            }
        }
        return make;
    }

    public void draw() {

        for (int i = -this.leftButton.getWidth() - 2; i < this.rightButton.getWidth() + 1; i++) {
            for (int z = -2; z < 4; z++) {
                this.textGraphics.putString(new TerminalPosition(x + i, y - z), " ");
            }
        }

        this.rightButton.draw();
        this.leftButton.draw();

        this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

        for (int i = this.leftButton.getPosition().getColumn() - 1; i < this.rightButton.getPosition().getColumn() + this.rightButton.getWidth() + 1; i++) {
            this.textGraphics.putString(new TerminalPosition(i, this.y - 3), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
            this.textGraphics.putString(new TerminalPosition(i, this.y + 3), String.valueOf(Symbols.DOUBLE_LINE_HORIZONTAL));
        }

        this.textGraphics.putString(new TerminalPosition(this.leftButton.getPosition().getColumn() - 2, this.y - 3), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(this.leftButton.getPosition().getColumn() - 2, this.y + 3), String.valueOf(Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER));
        this.textGraphics.putString(new TerminalPosition(this.rightButton.getPosition().getColumn() + this.rightButton.getWidth() + 1, this.y - 3), String.valueOf(Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER));
        this.textGraphics.putString(new TerminalPosition(this.rightButton.getPosition().getColumn() + this.rightButton.getWidth() + 1, this.y + 3), String.valueOf(Symbols.DOUBLE_LINE_TOP_LEFT_CORNER));

        for (int i = -2; i < 3; i++) {
            this.textGraphics.putString(new TerminalPosition(this.leftButton.getPosition().getColumn() - 2, this.y + i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
            this.textGraphics.putString(new TerminalPosition(this.rightButton.getPosition().getColumn() + this.rightButton.getWidth() + 1, this.y + i), String.valueOf(Symbols.DOUBLE_LINE_VERTICAL));
        }

        this.textGraphics.putString(new TerminalPosition(x - (this.information.length() / 2), y - 2), this.information);
    }
}
