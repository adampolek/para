package Characters;

import java.util.List;

public class EnemyTank extends Character {

    @Override
    public void attack1(Character target) {
        target.setHp(target.getHp() - getAttackDamage(1, 5, target));
    }

    @Override
    public void attack2(List<Character> targets) {
        for (Character target : targets) {
            target.setHp(target.getHp() - getAttackDamage(0, 2, target));
        }
    }

    @Override
    public void attack3(Character target) {
        target.setHp(target.getHp() - getAttackDamage(2, 3, target));
    }

    public EnemyTank() {
        this.setHp(generateRandomNumber(80, 130));                        // ustawiam hp na losow� warto�� od 120 do 160
        this.generateRandomName();
        this.setAttack(generateRandomNumber(2, 6));
        this.setDefence(generateRandomNumber(14, 16));
        this.setDodge(generateRandomNumber(6, 11));
        this.setSpeed(generateRandomNumber(7, 9));
        this.setDodge(generateRandomNumber(3, 4));
        this.setCrit_chance(15);
        this.setType("EnemyTank");
    }

}
