package Buildings;

import Characters.Character;
import Maps.Map;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Castle extends Building {
    private List<Map> maps = new ArrayList<Map>();
    private List<Character> characters = new ArrayList<>();

    public void addCharcters(Character character) {
        characters.add(character);
    }

    public void change(int index, Character character) {
        characters.add(index, character);
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void removeCharacters(Character character) {
        characters.remove(character);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Map selectMap(int option){
        return maps.get(option);
    }

    public void loadMap(String filename, String name) throws FileNotFoundException {
        Map ma = new Map(name);
        ma.makeMap(filename);
        maps.add(ma);
    }
}
