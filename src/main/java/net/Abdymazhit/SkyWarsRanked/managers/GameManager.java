package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер игры, отвечает за работу игры
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class GameManager {

    /** {@link GameStage Стадия игры} */
    private GameStage gameStage;

    /** Список игроков игры */
    private final List<Player> players;

    /** Список зрителей игры */
    private final List<Player> spectators;

    /** Хранит {@link PlayerInfo информацию} о игроке */
    private final Map<Player, PlayerInfo> playersInfo;

    /** Таймер обратного отсчета */
    private BukkitTask task;

    /**
     * Инициализирует нужные объекты
     */
    public GameManager() {
        gameStage = GameStage.WAITING;
        players = new ArrayList<>();
        spectators = new ArrayList<>();
        playersInfo = new HashMap<>();
    }

    /**
     * Устанавливает {@link GameStage стадию игры}
     * @param gameStage {@link GameStage Стадия игры}
     */
    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    /** Получает {@link GameStage стадию игры}
     * @return {@link GameStage Стадия игры}
     */
    public GameStage getGameStage() {
        return gameStage;
    }

    /**
     * Добавляет игрока в игру
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        playersInfo.put(player, new PlayerInfo(player));

        player.setFireTicks(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);

        // Выдать игроку предметы лобби
        SkyWarsRanked.getGameItems().giveLobbyItems(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(Config.lobbyLocation);

        spectators.remove(player);
        players.add(player);

        // Попытаться начать игру
        SkyWarsRanked.getGameStageManager().tryStartStartingStage();
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

        // Выдать зрителю предметы зрителя
        SkyWarsRanked.getGameItems().giveSpectatorItems(player);

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

    /** Получает {@link PlayerInfo информацию} о игроке
     * @return {@link PlayerInfo Информация} о игроке
     */
    public PlayerInfo getPlayerInfo(Player player) {
        return playersInfo.get(player);
    }

    /**
     * Установить таймер обратного отсчета
     * @param task Таймер обратного отсчета
     */
    public void setTask(BukkitTask task) {
        this.task = task;
    }

    /**
     * Получает таймер обратного отсчета
     * @return Таймер обратного отсчета
     */
    public BukkitTask getTask() {
        return task;
    }
}