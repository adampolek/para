package Controls;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class MyButton {

    private TextGraphics textGraphics;
    private String label = "Button";
    private TerminalPosition position;
    private int width = 30;
    private int heigth = 3;
    private TextColor activeColor = new TextColor.RGB(200, 100, 100);
    private Boolean active = false;
    private Action action;

    public boolean go() throws IOException {
        return action.run();
    }

    public TerminalPosition getPosition() {
        return position;
    }

    public String getLabel() {
        return label;
    }

    public MyButton(TextGraphics textGraphics, TerminalPosition position) {
        this.textGraphics = textGraphics;
        this.position = position;
        this.action = new Action() {
            @Override
            public boolean run() throws IOException {
                return false;
            }
        };
    }

    public MyButton(TextGraphics textGraphics, TerminalPosition position, Action action) {
        this.textGraphics = textGraphics;
        this.position = position;
        this.action = action;
    }

    public void setLabel(String label) {
        this.label = label;
        if ((this.width - 12) <= label.length()) {
            this.width = label.length() + 12;
        }
    }

    public void setPosition(TerminalPosition position) {
        this.position = position;
    }

    public void setWidth(int width) {
        if (width > (this.label.length() + 12))
            this.width = width;
    }

    public void setActiveColor(TextColor activeColor) {
        this.activeColor = activeColor;
    }

    public void setActive() {
        this.active = !this.active;
        this.draw();
    }

    public boolean getActive() {
        return active;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void draw() {

        if (active) {
            this.textGraphics.setForegroundColor(this.activeColor);

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow()), "|");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow() + 1), "|");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow() + 2), "|");

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 6, this.position.getRow() + 1), ">");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 7, this.position.getRow() + 1), ">");

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 7, this.position.getRow() + 1), "<");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 6, this.position.getRow() + 1), "<");

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow()), "|");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow() + 1), "|");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow() + 2), "|");
        } else {
            this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow()), " ");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow() + 1), " ");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 2, this.position.getRow() + 2), " ");

            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow()), " ");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow() + 1), " ");
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 3, this.position.getRow() + 2), " ");
        }

        this.textGraphics.putString(new TerminalPosition(this.position.getColumn(), this.position.getRow() + 1), "<");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 1, this.position.getRow()), "/");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 1, this.position.getRow() + 2), "\\");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 3, this.position.getRow()), "\\");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + 3, this.position.getRow() + 2), "/");


        for (int i = 4; i < this.width - 4; i++) {
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + i, this.position.getRow()), String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            if (!active) {
                this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + i, this.position.getRow() + 1), " ");
            }
            this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + i, this.position.getRow() + 2), String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        TerminalPosition textPosition = new TerminalPosition(position.getColumn() + ((this.width - this.label.length()) / 2), position.getRow() + 1);
        this.textGraphics.putString(textPosition, label, SGR.BOLD);

        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 4, this.position.getRow()), "/");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 4, this.position.getRow() + 2), "\\");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 2, this.position.getRow()), "\\");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 2, this.position.getRow() + 2), "/");
        this.textGraphics.putString(new TerminalPosition(this.position.getColumn() + this.width - 1, this.position.getRow() + 1), ">");

    }

}
