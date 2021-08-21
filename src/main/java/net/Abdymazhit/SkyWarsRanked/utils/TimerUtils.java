package net.Abdymazhit.SkyWarsRanked.utils;

/**
 * Отвечает за конвертацию времени в строку для использования в scoreboard'ах
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class TimerUtils {

    /**
     * Конвертирует время в строку
     * @param time Время
     * @return Время для использования в scoreboard'ах
     */
    public static String timeToString(int time) {
        int min = time / 60 % 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);
    }
}