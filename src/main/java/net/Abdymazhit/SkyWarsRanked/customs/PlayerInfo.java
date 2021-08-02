package net.Abdymazhit.SkyWarsRanked.customs;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Представляет собой информацию о игроке
 *
 * @version   02.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerInfo {

    /** Игрок, чья информация */
    private final Player player;

    /** Количество убийств игрока */
    private int kills;

    /** Местоположение смерти игрока */
    private Location deathLocation;

    /**
     * Создает информацию о игроке
     * @param player Игрок, чья информация
     */
    public PlayerInfo(Player player) {
        this.player = player;
        this.kills = 0;
        deathLocation = null;
    }

    /**
     * Получает игрока, чья информация
     * @return Игрока, чья информация
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Увеличивает количество убийств игрока
     */
    public void addKills() {
        kills++;
    }

    /**
     * Получает количество убийств игрока
     * @return Количество убийств игрока
     */
    public int getKills() {
        return kills;
    }

    /**
     * Устанавливает местоположение смерти игрока
     * @param deathLocation Местоположение смерти игрока
     */
    public void setDeathLocation(Location deathLocation) {
        this.deathLocation = deathLocation;
    }

    /**
     * Получает местоположение смерти игрока
     * @return Местоположение смерти игрока
     */
    public Location getDeathLocation() {
        return deathLocation;
    }
}