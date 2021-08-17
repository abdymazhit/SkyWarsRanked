package net.Abdymazhit.SkyWarsRanked.customs;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет собой остров
 *
 * @version   17.08.2021
 * @author    Islam Abdymazhit
 */
public class Island {

    /** Id острова */
    private final int id;

    /** Тег острова */
    private final char tag;

    /** Местоположение спавна острова */
    private final Location spawn;

    /** Список местоположений сундуков острова */
    private final List<Location> chests;

    /** Игроки острова */
    private final List<Player> players;

    /**
     * Создает новый остров
     * @param id Id острова
     * @param spawn Местоположение спавна острова
     * @param chests Список местоположений сундуков острова
     */
    public Island(int id, char tag, Location spawn, List<Location> chests) {
        this.id = id;
        this.tag = tag;
        this.spawn = spawn;
        this.chests = chests;
        this.players = new ArrayList<>();
    }

    /**
     * Получает id острова
     * @return Id острова
     */
    public int getId() {
        return id;
    }

    /**
     * Получает тег острова
     * @return Тег острова
     */
    public char getTag() {
        return tag;
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
     * Добавляет игрока в остров
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Удаляет игрока из острова
     * @param player Игрок
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Получает игроков острова
     * @return Игроки острова
     */
    public List<Player> getPlayers() {
        return players;
    }
}