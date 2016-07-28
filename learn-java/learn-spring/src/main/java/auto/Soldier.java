package auto;

import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 16/7/26
 */
@Component("sb")
public class Soldier {
    private Weapon weapon;

    public Soldier(){

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String attack(){
        String attack = weapon.attack();
        return attack;
    }
}
