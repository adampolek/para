import MenuPlay.ElementsMenu.Music;
import MenuPlay.MenuFirst;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        DefaultTerminalFactory terminal = new DefaultTerminalFactory();
        terminal.setTerminalEmulatorTitle("PARA");
        terminal.setInitialTerminalSize(new TerminalSize(screenWidth / 8, screenHeight / 16 - 3));

        Screen screen = terminal.createScreen();
        screen.setCursorPosition(null);
        screen.startScreen();

        Music.play();

        MenuFirst menu = new MenuFirst(screen);
        menu.create();
        menu.draw();
        menu.choice();


    }
}
