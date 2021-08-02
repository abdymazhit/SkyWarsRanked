package net.Abdymazhit.SkyWarsRanked;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Главный класс, отвечает за весь плагин
 *
 * @version   02.08.2021
 * @author    Islam Abdymazhit
 */
public class SkyWarsRanked extends JavaPlugin {

    /** Экземпляр плагина */
    private static SkyWarsRanked instance;

    /**
     * Событие включения плагина
     * <p>
     * Инициализирует нужные объекты для работы плагина
     */
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
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
}