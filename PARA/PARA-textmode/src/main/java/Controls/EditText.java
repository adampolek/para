package Controls;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class EditText {

    private String label = "Empty:";
    private String edittext = "";
    private Screen screen;
    private TextGraphics textGraphics;
    private boolean active = false;
    private int textx;
    private int texty;
    private int x;
    private int y;
    private int cursor = 0;
    private TextColor activeColor = new TextColor.RGB(200, 100, 100);
    private Action action;

    public boolean go() throws IOException {
        return action.run();
    }

    public int getY() {
        return y;
    }

    public void setLabel(String label) {
        this.label = label;
        this.textx = this.x + this.label.length() + 9;
    }

    public String getEdittext() {
        return edittext;
    }

    public EditText(final Screen screen, int x, int y) {
        this.screen = screen;
        this.textGraphics = screen.newTextGraphics();
        this.x = x;
        this.y = y;
        this.textx = this.x + this.label.length() + 9;
        this.texty = this.y + 1;
        this.action = new Action() {
            @Override
            public boolean run() throws IOException {
                boolean run = true;


                while (run) {
                    textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
                    textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
                    KeyStroke key = screen.pollInput();

                    if (key != null) {
                        if (key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.Enter) {
                            run = false;
                            cursor = 2;
                        } else if (key.getKeyType() == KeyType.Backspace) {
                            if (edittext.length() != 0) {
                                edittext = edittext.substring(0, edittext.length() - 1);
                            }
                        } else if (key.getKeyType() == KeyType.Character) {
                            if (edittext.length() != 20) {
                                edittext = edittext + key.getCharacter();
                            }
                        }
                    }
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                    textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
                    draw();
                    cursoraction();
                    screen.refresh();
                }


                return true;
            }
        };
    }

    public void setActive() {
        this.active = !this.active;
        this.draw();
    }

    public void cursoraction() {
        if (this.cursor == 0) {
            this.cursor = (this.cursor + 1) % 5;
            this.textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 9 + this.edittext.length(), this.y + 1), " ");
        } else {
            this.cursor = (this.cursor + 1) % 5;

        }
    }

    public boolean getActive() {
        return active;
    }

    public void draw() {
        if (this.active) {
            this.textGraphics.setForegroundColor(this.activeColor);
            this.textGraphics.putString(new TerminalPosition(this.x + 7, this.y + 1), this.label, SGR.BOLD);
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y), "|");
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y + 1), "|");
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y + 2), "|");

            this.textGraphics.putString(new TerminalPosition(this.x + 4, this.y + 1), ">");
            this.textGraphics.putString(new TerminalPosition(this.x + 5, this.y + 1), ">");

            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y), "|");
            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y + 1), "|");
            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y + 2), "|");
        } else {
            this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            this.textGraphics.putString(new TerminalPosition(this.x + 7, this.y + 1), this.label, SGR.BOLD);
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y), " ");
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y + 1), " ");
            this.textGraphics.putString(new TerminalPosition(this.x + 2, this.y + 2), " ");

            this.textGraphics.putString(new TerminalPosition(this.x + 4, this.y + 1), " ");
            this.textGraphics.putString(new TerminalPosition(this.x + 5, this.y + 1), " ");

            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y), " ");
            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y + 1), " ");
            this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 32, this.y + 2), " ");
        }

        this.textGraphics.putString(new TerminalPosition(this.x, this.y + 1), "<");
        this.textGraphics.putString(new TerminalPosition(this.x + 1, this.y), "/");
        this.textGraphics.putString(new TerminalPosition(this.x + 1, this.y + 2), "\\");
        this.textGraphics.putString(new TerminalPosition(this.x + 3, this.y), "\\");
        this.textGraphics.putString(new TerminalPosition(this.x + 3, this.y + 2), "/");

        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 34, this.y + 1), ">");
        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 31, this.y), "/");
        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 31, this.y + 2), "\\");
        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 33, this.y), "\\");
        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 33, this.y + 2), "/");

        for (int i = this.x + 4; i < this.x + this.label.length() + 31; i++) {
            this.textGraphics.putString(new TerminalPosition(i, this.y), String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            this.textGraphics.putString(new TerminalPosition(i, this.y + 2), String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        this.textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
        this.textGraphics.setForegroundColor(TextColor.ANSI.BLACK);

        this.textGraphics.putString(new TerminalPosition(textx, texty), edittext);

        for (int i = this.x + this.label.length() + 9 + this.edittext.length(); i < this.x + this.label.length() + 30; i++) {
            this.textGraphics.putString(new TerminalPosition(i, this.y + 1), " ");
        }

        this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
        this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);


    }

}
