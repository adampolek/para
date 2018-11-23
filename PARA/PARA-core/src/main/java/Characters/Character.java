package Characters;

import java.util.List;
import java.util.Random;

public class Character {
    private String name;
    private Integer hp;
    private Integer hp_level=1;
    private Integer attack;
    private Integer attack_level=1;
    private Integer defence;
    private Integer defence_level=1;
    private Integer speed;
    private Integer speed_level=1;
    private Integer dodge;
    private Integer dodge_level=1;
    private Integer crit_chance;
    private Integer crit_chance_level=1;
    private String type = "Character";

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Character() {
    }

    public void generateRandomName(){
        String[] beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
                "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
                "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
                "Mar", "Luk" };
        String[] middle = { "air", "ir", "mi", "sor", "mee", "clo",
                "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
                "marac", "zoir", "slamar", "salmar", "urak" };
        String[] end = { "d", "ed", "ark", "arc", "es", "er", "der",
                "tron", "med", "ure", "zur", "cred", "mur" , "alf"};
        Random rand = new Random();
        this.name = beginning[rand.nextInt(beginning.length)] +
                middle[rand.nextInt(middle.length)]+
                end[rand.nextInt(end.length)];
    }

    public Integer getHp_level() {
        return hp_level;
    }

    public void setHp_level(Integer hp_level) {
        this.hp_level = hp_level;
    }

    public Integer getAttack_level() {
        return attack_level;
    }

    public void setAttack_level(Integer attack_level) {
        this.attack_level = attack_level;
    }

    public Integer getDefence_level() {
        return defence_level;
    }

    public void setDefence_level(Integer defence_level) {
        this.defence_level = defence_level;
    }

    public Integer getSpeed_level() {
        return speed_level;
    }

    public void setSpeed_level(Integer speed_level) {
        this.speed_level = speed_level;
    }

    public Integer getDodge_level() {
        return dodge_level;
    }

    public void setDodge_level(Integer dodge_level) {
        this.dodge_level = dodge_level;
    }

    public Integer getCrit_chance_level() {
        return crit_chance_level;
    }

    public void setCrit_chance_level(Integer crit_chance_level) {
        this.crit_chance_level = crit_chance_level;
    }

    public Integer getCrit_chance() {
        return crit_chance;
    }

    public void setCrit_chance(Integer crit_chance) {
        this.crit_chance = crit_chance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setDodge(Integer dodge) {
        this.dodge = dodge;
    }

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefence() {
        return defence;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getDodge() {
        return dodge;
    }

    public int generateRandomNumber(int bottom_range, int upper_range){
        Random random = new Random();
        int value = random.nextInt(upper_range-bottom_range)+ (bottom_range+1);
        return value;
    }

    public Integer getAttackDamage(int bottom_range, int upper_range, Character target) {
        if (!makeDodge()) {
            if (this.getAttack() > target.getDefence()) {
                if (!makeCrit())
                    return (generateRandomNumber(bottom_range, upper_range) + this.getAttack() - target.getDefence());
                else
                    return 2 * (generateRandomNumber(bottom_range, upper_range) + this.getAttack() - target.getDefence());
            } else
                return (generateRandomNumber(1, 3));
        } else return 0;
    }

    public boolean makeDodge() {
        int value = generateRandomNumber(1, 200);
        if (value <= this.getDodge()) {
            return true;
        } else
            return false;
    }

    public boolean makeCrit() {
        int value = generateRandomNumber(1, 200);
        if (value <= getCrit_chance()) {
            return true;
        } else
            return false;
    }

    public void attack1(Character target) {
    }

    public void attack2(List<Character> targets) {
    }

    public void attack3(Character target) {
    }

    public Character(Character character) {
        this.name = character.getName();
        this.hp = character.getHp();
        this.hp_level = character.getHp_level();
        this.attack = character.getAttack();
        this.attack_level = character.getAttack_level();
        this.defence = character.getDefence();
        this.defence_level = character.getDefence_level();
        this.speed = character.getSpeed();
        this.speed_level = character.getSpeed_level();
        this.dodge = character.getDodge();
        this.dodge_level = character.getDodge_level();
        this.crit_chance = character.getCrit_chance();
        this.crit_chance_level = character.getCrit_chance_level();
        this.type = character.getType();
    }
}
