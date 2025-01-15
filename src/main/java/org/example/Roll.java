package org.example;

import java.util.ArrayList;

import static org.example.Decoding.decodingD6Heap;
import static org.example.Decoding.decodingPbta;

public class Roll {

    //бросок d6
    static int oneRoll() {
        int result = (int) (Math.random() * 6) + 1;
        return result;
    }

    //по дефолту всегда кидаем 2
    static String roll() {
        return roll(2);
    }

    // бросок того количества d6 сколько указанно в параметре
    static String roll(Integer d) {
        if (d == null)
            d = 2;
        ArrayList<Integer> buff = new ArrayList<>(d);
        while (d >= 1) {
            buff.add(oneRoll());
            d--;
        }
        int summ = buff.stream().mapToInt(Integer::intValue).sum();
        String result;
        //todo можно превратить в литерал, но возможно это превратится в свитч с другими вариантами
        if (buff.size() == 2)
            result = decodingPbta(summ, buff);
        else
            result = decodingD6Heap(summ, buff);
        return result;
    }
}
