package com.mushan;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by mazhibin on 16/9/5
 */
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DemoModule());
        Soldier soldier = injector.getInstance(Soldier.class);
        soldier.attack();
    }
}
