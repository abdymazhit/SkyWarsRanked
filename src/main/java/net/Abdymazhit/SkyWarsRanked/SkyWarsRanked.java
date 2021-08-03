package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.events.PlayerJoinListener;
import net.Abdymazhit.SkyWarsRanked.events.PlayerQuitListener;
import net.Abdymazhit.SkyWarsRanked.managers.GameManager;
import net.Abdymazhit.SkyWarsRanked.scoreboards.LobbyBoard;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class SkyWarsRanked extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWarsRanked instance;

    /** Менеджер игры, отвечает за работу игры */
    private static GameManager gameManager;

    /** Scoreboard стадии лобби игры */
    private static LobbyBoard lobbyBoard;

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

        gameManager = new GameManager();
        lobbyBoard = new LobbyBoard();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
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
     * Получает менеджер игры
     * @return Менеджер игры
     */
    public static GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Получает scoreboard стадии лобби игры
     * @return Scoreboard стадии лобби игры
     */
    public static LobbyBoard getLobbyBoard() {
        return lobbyBoard;
    }
}