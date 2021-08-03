package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер игры, отвечает за работу игры
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class GameManager {

    /** Стадия игры */
    private GameStage gameStage;

    /** Список игроков игры */
    private List<Player> players;

    /** Список зрителей игры */
    private List<Player> spectators;

    /**
     * Инициализирует нужные объекты
     */
    public GameManager() {
        gameStage = GameStage.WAITING;
        players = new ArrayList<>();
        spectators = new ArrayList<>();
    }

    /**
     * Устанавливает стадию игры
     * @param gameStage Стадия игры
     */
    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    /** Получает стадию игры
     * @return Стадия игры
     */
    public GameStage getGameStage() {
        return gameStage;
    }

    /**
     * Добавляет игрока в игру
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        player.setFireTicks(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(Config.lobbyLocation);

        spectators.remove(player);
        players.add(player);
    }

    /**
     * Удаляет игрока из игры
     * @param player Игрок
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /** Получает список игроков игры
     * @return Список игроков игры
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Добавляет зрителя в игру
     * @param player Зритель
     */
    public void addSpectator(Player player) {
        player.setFireTicks(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);

        player.setGameMode(GameMode.ADVENTURE);

        player.setAllowFlight(true);
        player.setFlying(true);

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }

        players.remove(player);
        spectators.add(player);
    }

    /**
     * Удаляет зрителя из игры
     * @param player Зритель
     */
    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    /** Получает список зрителей игры
     * @return Список зрителей игры
     */
    public List<Player> getSpectators() {
        return spectators;
    }
}