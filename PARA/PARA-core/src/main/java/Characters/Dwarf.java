package Characters;

import java.util.List;

public class Dwarf extends Character {


    @Override
    public void attack1(Character target){
        target.setHp(target.getHp() - getAttackDamage(3,7, target));
    }

    @Override
    public void attack2(List<Character> targets){
        for(Character target:targets){
            target.setHp(target.getHp() - getAttackDamage(1,2, target));
        }
    }

    @Override
    public void attack3(Character target){
        target.setHp(target.getHp()-getAttackDamage(4,6, target));
    }

    public Dwarf() {
        this.setHp(generateRandomNumber(120, 160));                        // ustawiam hp na losow� warto�� od 120 do 160
        this.generateRandomName();
        this.setAttack(generateRandomNumber(5, 9));
        this.setDefence(generateRandomNumber(17, 21));
        this.setDodge(generateRandomNumber(6, 11));
        this.setSpeed(generateRandomNumber(7, 12));
        this.setDodge(generateRandomNumber(3, 7));
        this.setCrit_chance(22);
        this.setType("Dwarf");
    }

    public Dwarf(Character character) {
        super(character);
    }
}
