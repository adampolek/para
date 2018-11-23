package Characters;

import java.util.List;

public class EnemyClose extends Character{

    @Override
    public void attack1(Character target) {
        target.setHp(target.getHp() - getAttackDamage(2, 6, target));
    }

    @Override
    public void attack2(List<Character> targets) {
        for (Character target : targets) {
            target.setHp(target.getHp() - getAttackDamage(1, 3, target));
        }
    }

    @Override
    public void attack3(Character target) {
        target.setHp(target.getHp() - getAttackDamage(2, 4, target));
    }

    public EnemyClose() {
        this.setHp(generateRandomNumber(60, 90));                      // ustawiam hp na losow� warto�� od 80 do 110
        this.generateRandomName();
        this.setAttack(generateRandomNumber(11, 13));
        this.setDefence(generateRandomNumber(9, 14));
        this.setDodge(generateRandomNumber(7, 11));
        this.setSpeed(generateRandomNumber(6, 14));
        this.setDodge(generateRandomNumber(6, 9));
        this.setCrit_chance(15);
        this.setType("EnemyClose");
    }

}
