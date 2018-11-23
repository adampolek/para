package Characters;

import java.util.List;

public class EnemyRange extends Character {
    @Override
    public void attack1(Character target) {
        target.setHp(target.getHp() - getAttackDamage(2, 9, target));
    }

    @Override
    public void attack2(List<Character> targets) {
        for (Character target : targets) {
            target.setHp(target.getHp() - getAttackDamage(0, 3, target));
        }
    }

    @Override
    public void attack3(Character target) {
        target.setHp(target.getHp() - getAttackDamage(4, 5, target));
    }

    public EnemyRange() {
        this.setHp(generateRandomNumber(40, 55));                         // ustawiam hp na losow� warto�� od 50 do 70
        this.generateRandomName();
        this.setAttack(generateRandomNumber(12, 18));
        this.setDefence(generateRandomNumber(5, 7));
        this.setDodge(generateRandomNumber(8, 14));
        this.setSpeed(generateRandomNumber(11, 16));
        this.setDodge(generateRandomNumber(7, 13));
        this.setCrit_chance(10);
        this.setType("EnemyRange");
    }

}
