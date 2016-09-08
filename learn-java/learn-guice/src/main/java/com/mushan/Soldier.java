package com.mushan;

/**
 * Created by mazhibin on 16/9/5
 */
public class Soldier {

    @com.google.inject.Inject
    private Sword weapon;

    public void attack(){
        weapon.attack();
    }
}
