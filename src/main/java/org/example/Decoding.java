package org.example;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Decoding {
    //разбор результат броска по пбт правилам
    public static String decodingPbta(int summ, ArrayList<Integer> buff) {
        String result = switch (summ) {
            case 6, 7, 8, 9 -> "частичный успех";
            case 10, 11, 12 -> "успех";
            default -> "провал";
        };
        return String.format("(%d + %d) = {%d}  %s", buff.get(0), buff.get(1), summ, result);
    }

    //разбор броска большого количества кубов
    public static String decodingD6Heap(int summ, ArrayList<Integer> buff) {
        String buffToString = buff.stream().map(String::valueOf)
                .collect(Collectors.joining("+", "(", ")"));
        return String.format("%s = {%d}", buffToString, summ);
    }
}
