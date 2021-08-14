package net.Abdymazhit.SkyWarsRanked.utils;

import net.Abdymazhit.SkyWarsRanked.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Отвечает за хранение системы рейтинга
 *
 * @version   14.08.2021
 * @author    Islam Abdymazhit
 */
public class RatingRepository {

    /** Рейтинги формата игры, в котором присутствуют 12 островов */
    private Map<String, Map<Integer, Integer>> ratings12Islands;

    /**
     * Инициализирует рейтинги форматов игры
     */
    public RatingRepository() {
        addRatings12Islands();
    }

    /**
     * Получает рейтинги нужного формата игры
     * @return Рейтинги нужного формата игры
     */
    public Map<String, Map<Integer, Integer>> getIslandRatings() {
        if(Config.islands.size() == 12) {
            return ratings12Islands;
        } else {
            return null;
        }
    }

    /**
     * Добавляет рейтинги формата игры, в котором присутствуют 12 островов
     */
    public void addRatings12Islands() {
        ratings12Islands = new HashMap<>();
        ratings12Islands.put("0-49", new HashMap<Integer, Integer>() {{
            put(1, 10);
            put(2, 8);
            put(3, 7);
            put(4, 6);
            put(5, 6);
            put(6, 4);
            put(7, 4);
            put(8, 2);
            put(9, 2);
            put(10, 1);
            put(11, 0);
            put(12, 0);
        }});

        ratings12Islands.put("50-99", new HashMap<Integer, Integer>() {{
            put(1, 9);
            put(2, 7);
            put(3, 6);
            put(4, 6);
            put(5, 4);
            put(6, 4);
            put(7, 2);
            put(8, 1);
            put(9, 0);
            put(10, 0);
            put(11, -1);
            put(12, -2);
        }});

        ratings12Islands.put("100-199", new HashMap<Integer, Integer>() {{
            put(1, 9);
            put(2, 7);
            put(3, 6);
            put(4, 4);
            put(5, 4);
            put(6, 2);
            put(7, 1);
            put(8, 0);
            put(9, -1);
            put(10, -2);
            put(11, -3);
            put(12, -4);
        }});

        ratings12Islands.put("200-299", new HashMap<Integer, Integer>() {{
            put(1, 8);
            put(2, 7);
            put(3, 6);
            put(4, 4);
            put(5, 2);
            put(6, 1);
            put(7, 0);
            put(8, -1);
            put(9, -2);
            put(10, -3);
            put(11, -4);
            put(12, -6);
        }});

        ratings12Islands.put("300-399", new HashMap<Integer, Integer>() {{
            put(1, 8);
            put(2, 6);
            put(3, 5);
            put(4, 2);
            put(5, 1);
            put(6, 0);
            put(7, -1);
            put(8, -2);
            put(9, -4);
            put(10, -6);
            put(11, -7);
            put(12, -9);
        }});

        ratings12Islands.put("400-499", new HashMap<Integer, Integer>() {{
            put(1, 6);
            put(2, 4);
            put(3, 2);
            put(4, 1);
            put(5, 0);
            put(6, -1);
            put(7, -2);
            put(8, -4);
            put(9, -6);
            put(10, -8);
            put(11, -10);
            put(12, -11);
        }});

        ratings12Islands.put("500+", new HashMap<Integer, Integer>() {{
            put(1, 5);
            put(2, 3);
            put(3, 1);
            put(4, 0);
            put(5, -1);
            put(6, -2);
            put(7, -4);
            put(8, -6);
            put(9, -8);
            put(10, -11);
            put(11, -12);
            put(12, -13);
        }});
    }
}