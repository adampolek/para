package Gameplay;

import Buildings.Castle;
import Buildings.Forge;
import Buildings.Inn;
import Characters.Character;
import Characters.*;
import Maps.Hero;
import Maps.HeroControll;
import Maps.Map;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Game {
    private User user = new User();
    private Inn inn = new Inn();
    private Castle castle = new Castle();
    private Forge forge = new Forge();
    private HeroControll heroControll = new HeroControll();
    private List<Character> characterInMission = new ArrayList<>();
    private List<Character> enemies = new ArrayList<>();
    private List<Character> queue = new ArrayList<>();
    private List<Integer> hp = new ArrayList<>();
    private int numberFight = 0;

    public void setNumberFight(int numberFight) {
        this.numberFight = numberFight;
    }

    public void createHero(Map map) {
        Hero hero = new Hero(map);
        map.setFightOnMap();
        heroControll.setH(hero);
    }

    public HeroControll getHeroControll() {
        return heroControll;
    }

    public User getUser() {
        return user;
    }

    public Inn getInn() {
        return inn;
    }

    public Castle getCastle() {
        return castle;
    }

    public Forge getForge() {
        return forge;
    }

    public List<Character> getCharacterInMission() {
        return characterInMission;
    }

    public List<Character> getEnemies() {
        return enemies;
    }

    public List<Character> getQueue() {
        return queue;
    }

    public void randomEnemies() {
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(3) + 1; i++) {
            Character enemy = null;
            switch (rand.nextInt(2) + 1) {
                case 1:
                    enemy = new EnemyClose();
                    break;
                case 2:
                    enemy = new EnemyRange();
                    break;
                case 3:
                    enemy = new EnemyTank();
                    break;
            }
            enemies.add(enemy);
        }
        setPriority();
    }

    public void setPriority() {
        queue.addAll(enemies);
        queue.addAll(characterInMission);
        queue.sort(Comparator.comparingInt(Character::getSpeed).reversed());
    }

    public void changeCellMap() {
        numberFight++;
        heroControll.getH().getMap().setCellMap(heroControll.getH().getX(), heroControll.getH().getY(), 1);
    }

    public void addCharacterToUser(Character character) {
        if (user.getUsersCharacters().size() < 24) {
            user.addCharacter(character);
            inn.deleteCharacter(character);
        }
    }

    public void goMission(Map map) {
        createHero(map);
        for (Character character : castle.getCharacters()) {
            hp.add(character.getHp());
            characterInMission.add(character);
        }
    }

    public void deletehp(int idx) {
        hp.remove(idx);
    }

    public void resetCharacterFromMission() {
        Random rand = new Random();
        for (Character character : characterInMission) {
            if (!castle.getCharacters().isEmpty()) {
                user.getUsersCharacters().remove(castle.getCharacters().get(0));
                user.getUsersCharacters().add(character);
                castle.getCharacters().get(0).setHp(hp.get(0));
                hp.remove(0);
                castle.getCharacters().remove(0);
            }
        }
        characterInMission.clear();
        castle.getCharacters().clear();
        enemies.clear();
        queue.clear();
        hp.clear();
        numberFight = 0;
    }

    public void backFromMission() {
        user.setGold(user.getGold() + numberFight * 25);
        if (inn.getCharacters().size() < 24) {
            inn.randomCharacter();
        }
        resetCharacterFromMission();
    }

    public void createFirst() {
        this.addCharacterToUser(inn.getCharacters().get(0));
        inn.randomCharacter();
        this.addCharacterToUser(inn.getCharacters().get(0));
        inn.randomCharacter();
        this.addCharacterToUser(inn.getCharacters().get(0));
        inn.randomCharacter();
    }

    public void loadMaps() {
        try {
            castle.loadMap("mapa1.txt", "Big map");
            castle.loadMap("mapa3.txt", "Medium map");
            castle.loadMap("mapa2.txt", "Small map");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Character enemyRandomAttack(Character character) {
        Random rd = new Random();
        int selcetedAttack = rd.nextInt(3);
        int selectedTarget = rd.nextInt(characterInMission.size());
        switch (selcetedAttack) {
            case 0:
                character.attack1(characterInMission.get(selectedTarget));
                break;
            case 1:
                character.attack2(characterInMission);
                break;
            case 2:
                character.attack3(characterInMission.get(selectedTarget));
                break;
        }

        return characterInMission.get(selectedTarget);
    }

    public void deadHero(Character character) {
        castle.getCharacters().remove(character);
        characterInMission.remove(character);
        user.deleteCharacter(character);
        queue.remove(character);
    }

    public Character attackHero(Character character, Character target, int selectAttack) {
        switch (selectAttack) {
            case 0:
                character.attack1(enemies.get(enemies.indexOf(target)));
                break;
            case 1:
                character.attack2(enemies);
                break;
            case 2:
                character.attack3(enemies.get(enemies.indexOf(target)));
                break;
        }

        return enemies.get(enemies.indexOf(target));
    }

    public void attackWizard(Character character, int selectAttack) {
        Random rand = new Random();
        switch (selectAttack) {
            case 1:
                character.attack2(characterInMission);
                break;
            case 2:
                character.attack3(characterInMission.get(rand.nextInt(characterInMission.size())));
                break;
        }
    }

    public void deadEnemy(Character character) {
        enemies.remove(character);
        queue.remove(character);
    }

}
