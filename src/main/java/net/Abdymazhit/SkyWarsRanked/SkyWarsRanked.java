package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.managers.GameManager;
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
}