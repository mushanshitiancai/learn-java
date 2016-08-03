package com.mushan;

import auto.Soldier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mazhibin on 16/8/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:auto.xml")
public class DemoTest {

    @Autowired
    private Soldier soldier;

    @Test
    public void testSpring(){
        assertThat(soldier.attack(),is("gun attack!"));
    }
}
