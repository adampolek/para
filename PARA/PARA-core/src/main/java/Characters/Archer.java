package Characters;

import java.util.List;

public class Archer extends Character {

    @Override
    public void attack1(Character target){
        target.setHp(target.getHp() - getAttackDamage(5,13, target));
    }

    @Override
    public void attack2(List<Character> targets){
        for(Character target:targets){
            target.setHp(target.getHp() - getAttackDamage(2,5, target));
        }
    }

    @Override
    public void attack3(Character target){
        target.setHp(target.getHp()-getAttackDamage(8,10, target));
    }

    public Archer() {
        this.setHp(generateRandomNumber(50, 70));                         // ustawiam hp na losow� warto�� od 50 do 70
        this.generateRandomName();
        this.setAttack(generateRandomNumber(15, 22));
        this.setDefence(generateRandomNumber(5, 10));
        this.setDodge(generateRandomNumber(12, 16));
        this.setSpeed(generateRandomNumber(14, 19));
        this.setDodge(generateRandomNumber(11, 15));
        this.setCrit_chance(10);
        this.setType("Archer");
    }

}
