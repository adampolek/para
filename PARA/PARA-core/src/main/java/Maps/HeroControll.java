package Maps;

// klasa do sterowania bohaterem (zmienia wspolrzedne)
public class HeroControll {
    private Hero h;

    public Hero getH() {
        return h;
    }

    public void setH(Hero h) {
        this.h = h;
    }

    //metody do poruszania sie
    public void goUp() {
        if ((h.getX() - 2) >= 0 && (h.getX() - 2) <= 24) {
            if (h.getVarible((h.getX() - 2), h.getY()) == 1 || h.getVarible(h.getX() - 2, h.getY()) == 2 || h.getVarible(h.getX() + 2, h.getY()) == 3) {
                h.setX(h.getX() - 2);
            }
        }
    }

    public void goDown() {
        if ((h.getX() + 2) >= 0 && (h.getX() + 2) <= 24) {
            if (h.getVarible(h.getX() + 2, h.getY()) == 1 || h.getVarible(h.getX() + 2, h.getY()) == 2 || h.getVarible(h.getX() + 2, h.getY()) == 3) {
                h.setX(h.getX() + 2);
            }
        }
    }

    public void goLeft() {
        if ((h.getY() - 2) >= 0 && (h.getY() - 2) <= 24) {
            if (h.getVarible(h.getX(), h.getY() - 2) == 1 || h.getVarible(h.getX(), h.getY() - 2) == 2 || h.getVarible(h.getX() + 2, h.getY()) == 3) {
                h.setY(h.getY() - 2);
            }
        }
    }

    public void goRight() {
        if ((h.getY() + 2) >= 0 && (h.getY() + 2) <= 24) {
            if (h.getVarible(h.getX(), h.getY() + 2) == 1 || h.getVarible(h.getX(), h.getY() + 2) == 2 || h.getVarible(h.getX() + 2, h.getY()) == 3) {
                h.setY(h.getY() + 2);
            }
        }
    }

    public boolean exit() {
        if (h.getVarible(h.getX(), h.getY()) == 3) {
            return true;
        }
        return false;
    }

    public boolean startFight() {
        if (h.getVarible(h.getX(), h.getY()) == 2) {
            return true;
        }
        return false;
    }
}
