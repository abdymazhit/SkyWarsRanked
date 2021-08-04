package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.events.PlayerJoinListener;
import net.Abdymazhit.SkyWarsRanked.events.PlayerLoginListener;
import net.Abdymazhit.SkyWarsRanked.events.PlayerQuitListener;
import net.Abdymazhit.SkyWarsRanked.events.cancelled.*;
import net.Abdymazhit.SkyWarsRanked.items.GameItems;
import net.Abdymazhit.SkyWarsRanked.managers.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.managers.GameManager;
import net.Abdymazhit.SkyWarsRanked.managers.GameStageManager;
import net.Abdymazhit.SkyWarsRanked.scoreboards.GameBoard;
import net.Abdymazhit.SkyWarsRanked.scoreboards.LobbyBoard;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class SkyWarsRanked extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWarsRanked instance;

    /** Объект, отвечает за работу с API */
    private static API api;

    /** Менеджер игры, отвечает за работу игры */
    private static GameManager gameManager;

    /** Менеджер стадии игры, отвечает за стадии игры */
    private static GameStageManager gameStageManager;

    /** Менеджер игровые событий, отвечает за игровые события */
    private static GameEventsManager gameEventsManager;

    /** Объект, отвечающий за scoreboard лобби */
    private static LobbyBoard lobbyBoard;

    /** Объект, отвечающий за scoreboard игры */
    private static GameBoard gameBoard;

    /** Объект, отвечающий за игровые предметы */
    private static GameItems gameItems;

    /**
     * Событие включения плагина
     * <p>
     * Инициализирует нужные объекты для работы плагина
     */
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        Config.load();

        api = new API();
        gameManager = new GameManager();
        gameStageManager = new GameStageManager();
        gameEventsManager = new GameEventsManager();
        lobbyBoard = new LobbyBoard();
        gameBoard = new GameBoard();
        gameItems = new GameItems();

        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        getServer().getPluginManager().registerEvents(new BlockEventsListener(), this);
        getServer().getPluginManager().registerEvents(new EntityEventsListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryEventsListener(), this);
        getServer().getPluginManager().registerEvents(new PaintingEventsListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerEventsListener(), this);
        getServer().getPluginManager().registerEvents(new WeatherEventsListener(), this);
        getServer().getPluginManager().registerEvents(new WorldEventsListener(), this);
    }

    /**
     * Событие выключения плагина
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Получает экземпляр плагина
     * @return Экземпляр плагина
     */
    public static SkyWarsRanked getInstance() {
        return instance;
    }

    /**
     * Получает объект, отвечающий за работы с API
     * @return Объект, отвечающий за работы с API
     */
    public static API getApi() {
        return api;
    }

    /**
     * Получает менеджер игры
     * @return Менеджер игры
     */
    public static GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Получает менеджер стадии игры
     * @return Менеджер стадии игры
     */
    public static GameStageManager getGameStageManager() {
        return gameStageManager;
    }

    /**
     * Получает менеджер игровых событий
     * @return Менеджер игровых событий
     */
    public static GameEventsManager getGameEventsManager() {
        return gameEventsManager;
    }

    /**
     * Получает объект, отвечающий за scoreboard лобби
     * @return Объект, отвечающий за scoreboard лобби
     */
    public static LobbyBoard getLobbyBoard() {
        return lobbyBoard;
    }

    /**
     * Получает объект, отвечающий за scoreboard игры
     * @return Объект, отвечающий за scoreboard игры
     */
    public static GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Возвращает объект, отвечающий за игровые предметы
     * @return Объект, отвечающий за игровые предметы
     */
    public static GameItems getGameItems() {
        return gameItems;
    }
}