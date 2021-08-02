package net.Abdymazhit.SkyWarsRanked.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер игры, отвечает за работу игры
 *
 * @version   02.08.2021
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

    /** Получает список игроков игры
     * @return Список игроков игры
     */
    public List<Player> getPlayers() {
        return players;
    }

    /** Получает список зрителей игры
     * @return Список зрителей игры
     */
    public List<Player> getSpectators() {
        return spectators;
    }
}