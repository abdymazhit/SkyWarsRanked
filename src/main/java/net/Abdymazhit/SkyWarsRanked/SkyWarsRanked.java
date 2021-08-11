package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.game.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class SkyWarsRanked extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWarsRanked instance;

    /** Объект, отвечает за работу с API */
    private static API api;

    /** Объект, отвечает за работу с базой данных */
    private static MySQL mySQL;

    /** Менеджер игры, отвечает за работу игры */
    private static GameManager gameManager;

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
        mySQL = new MySQL();
        gameManager = new GameManager();
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
     * Получает объект, отвечающий за работы с базой данных
     * @return Объект, отвечающий за работы с базой данных
     */
    public static MySQL getMySQL() {
        return mySQL;
    }

    /**
     * Получает менеджер игры
     * @return Менеджер игры
     */
    public static GameManager getGameManager() {
        return gameManager;
    }
}