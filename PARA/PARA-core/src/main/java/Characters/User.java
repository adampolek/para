/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Slawek
 */
public class User {
    private String name;
    private int gold = 0;
    private List<Character> users_characters = new ArrayList<Character>();    //bo przeciez uzytkownik musi miec liste bohaterow

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGold(int gold){
        this.gold=gold;
    }
    public int getGold(){
        return gold;
    }
    public List<Character> getUsersCharacters(){
        return users_characters;
    }
    public void addCharacter(Character character){
        users_characters.add(character);
    }
    public void deleteCharacter(Character character) {
        users_characters.remove(character);
    }

    // Zapisuje do pliku w postaci:
    // [liczba postaci]
    // dla kazdej postaci:
    // [class [klasa postaci] [HP] [Hplevel] [Attack] [AttackLevel] itd.]
    // [user.gold]
    public void saveToFile(String filename) throws IOException {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file);
        writer.write(getName() + "\n");
        writer.write(users_characters.size() + "\n");
        for (Character character : users_characters) {
            writer.write(character.getClass() + " " + character.getHp() + " " + character.getHp_level() + " " + character.getAttack() + " " + character.getAttack_level() + " " +
                    character.getDefence() + " " + character.getDefence_level() + " " + character.getSpeed() + " " + character.getSpeed_level() + " " + character.getDodge() + " " +
                    character.getDodge_level() + " " + character.getCrit_chance() + " " + character.getCrit_chance_level() + "\n");
        }
        writer.write(String.valueOf(gold));
        writer.close();
    }

    public boolean loadFromFile(String name) throws IOException {
        File file = new File(name);
        if (!file.exists())
            return false;
        Scanner scanner = new Scanner(file);
        setName(scanner.next());
        int champions_number = scanner.nextInt();
        for (int i = 0; i < champions_number; i++) {
            scanner.next();
            String klasa = scanner.next();
            if (klasa.equals("Characters.Dwarf")) {
                Character character = new Dwarf();
                loadCharacter(character, scanner);
            } else if (klasa.equals("Characters.Archer")) {
                Character character = new Archer();
                loadCharacter(character, scanner);
            } else if (klasa.equals("Characters.Warrior")) {
                Character character = new Warrior();
                loadCharacter(character, scanner);
            } else if (klasa.equals("Characters.Wizard")) {
                Character character = new Wizard();
                loadCharacter(character, scanner);
            } else {
                return false;
            }
        }
        setGold(scanner.nextInt());
        return true;
    }

    public void loadCharacter(Character character, Scanner scan) {
        character.setHp(scan.nextInt());
        character.setHp_level(scan.nextInt());
        character.setAttack(scan.nextInt());
        character.setAttack_level(scan.nextInt());
        character.setDefence(scan.nextInt());
        character.setDefence_level(scan.nextInt());
        character.setSpeed(scan.nextInt());
        character.setSpeed_level(scan.nextInt());
        character.setDodge(scan.nextInt());
        character.setDodge_level(scan.nextInt());
        character.setCrit_chance(scan.nextInt());
        character.setCrit_chance_level(scan.nextInt());
        addCharacter(character);
    }
}
