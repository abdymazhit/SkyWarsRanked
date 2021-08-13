package net.Abdymazhit.SkyWarsRanked.utils;

import org.bukkit.Material;

import java.util.List;
import java.util.SplittableRandom;

/**
 * Отвечает за рандом
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class Random {

    /** Объект рандома */
    public static SplittableRandom random = new SplittableRandom();

    public static Material of(Material... materials) {
        return materials[random.nextInt(materials.length)];
    }

    @SafeVarargs
    public static <T> T of(List<T>... lists) {
        int var = 0;
        for (List<T> l : lists) {
            var += l.size();
        }
        var = random.nextInt(var);
        for (List<T> l : lists) {
            if (var < l.size()) {
                return l.get(var);
            }
            var -= l.size();
        }
        return null;
    }
}