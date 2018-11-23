package Controls;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Strip {

    private String label = "Empty";
    private TextGraphics textGraphics;
    private int select = 10;
    private boolean active = false;
    private int x;
    private int y;
    private TextColor activeColor = new TextColor.RGB(200, 100, 100);
    private Action action;

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSelect() {
        return select;
    }

    public Strip(TextGraphics textGraphics, int x, int y) {
        this.textGraphics = textGraphics;
        this.x = x;
        this.y = y;
        this.action = new Action() {
            @Override
            public boolean run() throws IOException {
                return false;
            }
        };
    }

    public Strip(TextGraphics textGraphics, int x, int y, Action action) {
        this.textGraphics = textGraphics;
        this.x = x;
        this.y = y;
        this.action = action;
    }

    public Strip(TextGraphics textGraphics, int x, int y, int select, Action action) {
        this.textGraphics = textGraphics;
        this.x = x;
        this.y = y;
        this.select = select;
        this.action = action;
    }

    boolean go() throws IOException {
        return action.run();
    }

    public void setSelect(int select) {
        if (this.select != 0 || this.select != 10) {
            this.select = select;
            try {
                this.go();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.draw();
        }
    }

    public void setActive() {
        this.active = !this.active;
        this.draw();
    }

    public boolean getActive() {
        return active;
    }

    void setAction(Action action) {
        this.action = action;
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

        this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 8, this.y + 1), String.valueOf(Symbols.TRIANGLE_LEFT_POINTING_BLACK));
        this.textGraphics.putString(new TerminalPosition(this.x + this.label.length() + 30, this.y + 1), String.valueOf(Symbols.TRIANGLE_RIGHT_POINTING_BLACK));


        int counter = 0;
        for (int i = this.x + this.label.length() + 10; i < this.x + this.label.length() + 29; i++) {
            if (counter < this.select) {
                this.textGraphics.setBackgroundColor(this.activeColor);
            } else {
                this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
            }
            this.textGraphics.putString(new TerminalPosition(i, this.y + 1), String.valueOf(Symbols.OUTLINED_SQUARE), SGR.BOLD);

            i++;
            this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
            this.textGraphics.putString(new TerminalPosition(i, this.y + 1), " ");
            counter++;
        }

        this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);


    }

}
