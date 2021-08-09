package net.Abdymazhit.SkyWarsRanked.customs;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Представляет собой остров
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class Island {

    /** Id острова */
    private final int id;

    /** Местоположение спавна острова */
    private final Location spawn;

    /** Список местоположений сундуков острова */
    private final List<Location> chests;

    /** Игрок острова */
    private Player player;

    /**
     * Создает новый остров
     * @param id Id острова
     * @param spawn Местоположение спавна острова
     * @param chests Список местоположений сундуков острова
     */
    public Island(int id, Location spawn, List<Location> chests) {
        this.id = id;
        this.spawn = spawn;
        this.chests = chests;
        this.player = null;
    }

    /**
     * Получает id острова
     * @return Id острова
     */
    public int getId() {
        return id;
    }

    /**
     * Получает местоположение спавна острова
     * @return Местоположение спавна острова
     */
    public Location getSpawn() {
        return spawn;
    }

    /**
     * Получает список местоположений сундуков острова
     * @return Список местоположений сундуков острова
     */
    public List<Location> getChests() {
        return chests;
    }

    /**
     * Устанавливает игрока острова
     * @param player Игрок острова
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Получает игрока острова
     * @return Игрок острова
     */
    public Player getPlayer() {
        return player;
    }
}