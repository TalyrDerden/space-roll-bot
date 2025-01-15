package org.example;

import org.junit.Assert;
import org.junit.Test;

public class RollTest {

    @Test
    public void rollTest() {
        System.out.println("default :" + Roll.roll());
        System.out.println("null :" + Roll.roll(null));
        for (int i = 0; i < 10; i++) {
            String r = Roll.roll(i);
            System.out.println(i + ":" + r);
        }
    }

    @Test
    public void oneRoll() {
        for (int i = 0; i < 100; i++) {
            int r = Roll.oneRoll();
            //System.out.println(i+":"+r);
            Assert.assertFalse(r + " :Бросок за пределы", 1 < r && r > 6);
        }
    }
}
