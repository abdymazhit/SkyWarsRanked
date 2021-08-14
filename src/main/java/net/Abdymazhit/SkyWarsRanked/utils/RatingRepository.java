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
    }
}