package Buildings;

import Characters.Character;
import Characters.User;

public class Forge extends Building {
    public boolean upgradeHp(Character ch, User user) {
        if(user.getGold()>=10*(ch.getHp_level())){
            ch.setHp(ch.getHp()+1);
            user.setGold(user.getGold()-(10*(ch.getHp_level())));
            ch.setHp_level(ch.getHp_level()+1);
            return true;
        }
        else{
            return false;
        }
        //ch.gold-=30 ulepszenie minus zasoby zależy jakie chcemy dać
        //ale myśle że tylko złoto ale to jeszcze if a trzeba żeby
        //sprawdzić czy tyle golda ma'
    }

    public boolean upgradeAttack(Character ch, User user) {
        if(user.getGold()>=10*(ch.getAttack_level())){
            ch.setAttack(ch.getAttack()+1);
            user.setGold(user.getGold()-(10*(ch.getAttack_level())));
            ch.setAttack_level(ch.getAttack_level()+1);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean upgradeDefence(Character ch, User user) {
        if(user.getGold()>=10*(ch.getDefence_level())){
            ch.setDefence(ch.getDefence()+1);
            user.setGold(user.getGold()-(10*(ch.getDefence_level())));
            ch.setDefence_level(ch.getDefence_level()+1);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean upgradeSpeed(Character ch, User user) {
        if(user.getGold()>=10*(ch.getSpeed_level())){
            ch.setSpeed(ch.getSpeed()+1);
            user.setGold(user.getGold()-10*(ch.getSpeed_level()));
            ch.setSpeed_level(ch.getSpeed_level()+1);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean upgradeDodge(Character ch, User user) {
        if(user.getGold()>=10*(ch.getDodge_level())){
            ch.setDodge(ch.getSpeed()+1);
            user.setGold(user.getGold()-10*(ch.getDodge_level()));
            ch.setDodge_level((ch.getDodge_level()+1));
            return true;
        }
        else{
            return false;
        }
    }

    public boolean upgradeCritChance(Character ch, User user) {
        if(user.getGold()>=10*ch.getCrit_chance_level()){
            ch.setCrit_chance(ch.getCrit_chance() + 1);
            user.setGold(user.getGold()-10*(ch.getCrit_chance_level()));
            ch.setCrit_chance_level(ch.getCrit_chance_level()+1);
            return true;
        }
        else{
            return false;
        }
    }
}
