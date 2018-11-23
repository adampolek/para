package Buildings;

import Characters.Character;
import Characters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inn extends Building {
    private List<Character> characters= new ArrayList<Character>();

    public void addArcher() {
        this.characters.add(new Archer());
    }

    public void addDwarf() {
        this.characters.add(new Dwarf());
    }

    public void addWarrior() {
        this.characters.add(new Warrior());
    }

    public void addWizard() {
        this.characters.add(new Wizard());
    }
    public int generateRandomNumber(int bottom_range, int upper_range){
        Random random = new Random();
        int value = random.nextInt(upper_range-bottom_range)+ (bottom_range+1);
        return value;
    }
    public List<Character> getCharacters(){
        return characters;
    }

    public void deleteCharacter(Character character) {
        characters.remove(character);
    }

    public void randomCharacter() {
        Random rand = new Random();
        switch (rand.nextInt(4) + 1) {
            case 1:
                this.addArcher();
                break;
            case 2:
                this.addDwarf();
                break;
            case 3:
                this.addWarrior();
                break;
            case 4:
                this.addWizard();
                break;
        }
    }

    public Inn() {
        this.randomCharacter();
    }
}
