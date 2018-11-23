package Controls;

import com.googlecode.lanterna.graphics.TextGraphics;

public class AttackButton {
    private TextGraphics textGraphics;
    private MyButton hero;
    private MyButton[] attacks = new MyButton[3];

    public AttackButton(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public MyButton getHero() {
        return hero;
    }

    public void setHero(MyButton hero) {
        this.hero = hero;
    }

    public MyButton[] getAttacks() {
        return attacks;
    }

    public void setAttacks(MyButton[] attacks) {
        this.attacks = attacks;
    }

    public void draw() {
        hero.draw();
        for (int i = 0; i < 3; i++) {
            attacks[i].draw();
        }
    }
}
