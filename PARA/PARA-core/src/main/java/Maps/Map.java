/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Sawek
 */
public class Map {
    private String name;
    int[][] mapa = new int[25][25];
    int counter = 0;
    int numberFights = 0;

    public String getName() {
        return name;
    }

    public Map(String name) {
        this.name = name;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                mapa[i][j] = 0;
            }
        }
    }

    public void setCellMap(int x, int y, int number) {
        mapa[x][y] = number;
    }

    public void makeMap(String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(name));
        int x=0, y=0;
        char znak;
        int t;
        String line;
        
        while(scanner.hasNextLine()){
            y = 0;
            line=scanner.nextLine();
            int length=line.length();
            for(int i=0;i<length;i++){
                znak = line.charAt(i);          //1 mozna wejsc(komnata) 0 nie mozna(puste pole)
                if (znak == '0') t = 0;
                else if (znak == '1') {
                    t = 1;
                    counter++;
                } else
                    t = 3;
                mapa[x][i] = t;
                y++;
            }
            x++;
           
        }
    }

    public void setFightOnMap() {   // ustawienie 2 czyli walki na mapie narazie nielosowo
        resetMap();
        Random rand = new Random();
        numberFights = rand.nextInt(10) + 1;
        List<Integer> wherefight = new ArrayList<>();
        int fightcounter = counter / numberFights;
        for (int i = 0; i < numberFights; i++) {
            wherefight.add(rand.nextInt(fightcounter) + i * fightcounter + 1);
        }
        if (wherefight.get(0) == 1) {
            wherefight.set(0, 2);
        }
        if (wherefight.get(numberFights - 1) > counter) {
            wherefight.set(numberFights - 1, counter - 1);
        }

        counter = 0;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (mapa[i][j] == 1) {
                    counter++;
                    if (!wherefight.isEmpty())
                        if (counter == wherefight.get(0)) {
                            mapa[i][j] = 2;
                            System.out.println(i + ", " + j);
                            wherefight.remove(0);
                        }
                }
            }
        }
    }

    public void resetMap() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (mapa[i][j] == 2) {
                    mapa[i][j] = 1;
                }
            }
        }
    }

    public int getNumberFights() {
        return numberFights;
    }

    public int[][] getMap() {
        return mapa;
    }

}
