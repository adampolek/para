/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

/**
 *
 * @author Sawek
 */
public class Hero {
    private int x = 0, y = 0;    // pozycja bohatera na ekranie(startowa)
    private Map map;

    public Hero(Map map) {
        this.map = map;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }

    public Map getMap() {
        return map;
    }

    public int getVarible(int x, int y) {
        int[][] cells = map.getMap();
        return cells[x][y];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
}
