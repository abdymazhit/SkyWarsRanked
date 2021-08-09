package net.Abdymazhit.SkyWarsRanked.utils;

import java.util.TreeMap;

/**
 * Отвечает за конвертацию обычных цифр в римские цифры
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class RomanNumber {

    /** Хранит обычные и римские цифры*/
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    /**
     * Получает римскую цифру
     * @param number Цифра
     * @return Римская цифра
     */
    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}