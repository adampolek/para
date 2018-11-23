package Characters;

import java.util.List;

public class Wizard extends Character {

    @Override
    public void attack1(Character target) {
        target.setHp(target.getHp() - getAttackDamage(1,14, target));
    }

    @Override
    public void attack2(List<Character> targets) {
        for(Character target:targets){
            target.setHp(target.getHp() + generateRandomNumber(1, 4));
        }
    }

    @Override
    public void attack3(Character target) {
        target.setHp(target.getHp() + generateRandomNumber(4,10));
    }

    public Wizard() {
        this.setHp(generateRandomNumber(65, 90));                       // ustawiam hp na losow� warto�� od 65 do 90
        this.generateRandomName();
        this.setAttack(generateRandomNumber(12, 14));
        this.setDefence(generateRandomNumber(7, 12));
        this.setDodge(generateRandomNumber(10, 15));
        this.setSpeed(generateRandomNumber(12, 14));
        this.setDodge(generateRandomNumber(9, 12));
        this.setCrit_chance(20);
        this.setType("Wizard");
    }

    public Wizard(Character character) {
        super(character);
    }
}
