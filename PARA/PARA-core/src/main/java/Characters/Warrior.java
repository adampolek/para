package Characters;

import java.util.List;

public class Warrior extends Character {

    @Override
    public void attack1(Character target){
        target.setHp(target.getHp() - getAttackDamage(4,9, target));
    }

    @Override
    public void attack2(List<Character> targets){
        for(Character target:targets){
            target.setHp(target.getHp() - getAttackDamage(2,5, target));
        }
    }

    @Override
    public void attack3(Character target){
        target.setHp(target.getHp()-getAttackDamage(6,7, target));
    }

    public Warrior() {
        this.setHp(generateRandomNumber(80, 110));                      // ustawiam hp na losow� warto�� od 80 do 110
        this.generateRandomName();
        this.setAttack(generateRandomNumber(11, 16));
        this.setDefence(generateRandomNumber(11, 17));
        this.setDodge(generateRandomNumber(9, 13));
        this.setSpeed(generateRandomNumber(10, 15));
        this.setDodge(generateRandomNumber(8, 11));
        this.setCrit_chance(15);
        this.setType("Warrior");
    }

    public Warrior(Character character) {
        super(character);
    }
}
