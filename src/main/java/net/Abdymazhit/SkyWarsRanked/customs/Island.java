package net.Abdymazhit.SkyWarsRanked.customs;

import org.bukkit.Location;

import java.util.List;

/**
 * Представляет собой остров
 *
 * @version   02.08.2021
 * @author    Islam Abdymazhit
 */
public class Island {

    /** Id острова */
    private final int id;

    /** Местоположение спавна острова */
    private final Location spawn;

    /** Список местоположений сундуков острова */
    private final List<Location> chests;

    /** {@link PlayerInfo Информация о игроке} острова */
    private PlayerInfo playerInfo;

    /**
     * Создает новый остров с заданными параметрами
     * @param id Id острова
     * @param spawn Местоположение спавна острова
     * @param chests Список местоположений сундуков острова
     */
    public Island(int id, Location spawn, List<Location> chests) {
        this.id = id;
        this.spawn = spawn;
        this.chests = chests;
        this.playerInfo = null;
    }

    /**
     * Устанавливает {@link PlayerInfo информацию о игроке} острова
     * @param playerInfo {@link PlayerInfo Информация о игроке} острова
     */
    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    /** Получает id острова
     * @return Id острова
     */
    public int getId() {
        return id;
    }

    /** Получает местоположение спавна острова
     * @return Местоположение спавна острова
     */
    public Location getSpawn() {
        return spawn;
    }

    /** Получает список местоположений сундуков острова
     * @return Список местоположений сундуков острова
     */
    public List<Location> getChests() {
        return chests;
    }

    /** Получает {@link PlayerInfo информацию о игроке} острова
     * @return {@link PlayerInfo Информация о игроке} острова
     */
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}