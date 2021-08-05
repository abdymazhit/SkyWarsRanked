package net.Abdymazhit.SkyWarsRanked.managers;

/**
 * Менеджер настроек игры, отвечает за настройки игры
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class GameSettingsManager {

    /** Хранит информацию, разрешено ли PvP */
    private boolean isEnabledPvP;

    /** Хранит информацию, открыт ли мистический сундук */
    private boolean isMysteryChestOpened;

    /**
     * Инициализирует нужные объекты
     */
    public GameSettingsManager() {
        isEnabledPvP = false;
        isMysteryChestOpened = false;
    }

    /**
     * Устанавливает значение, разрешено ли PvP
     * @param isEnabledPvP Значение, разрешено ли PvP
     */
    public void setEnabledPvP(boolean isEnabledPvP) {
        this.isEnabledPvP = isEnabledPvP;
    }

    /**
     * Получает значение, разрешено ли PvP
     * @return Значение, разрешено ли PvP
     */
    public boolean isEnabledPvP() {
        return isEnabledPvP;
    }

    /**
     * Устанавливает значение, открыт ли мистический сундук
     * @param isMysteryChestOpened Значение, открыт ли мистический сундук
     */
    public void setMysteryChestOpened(boolean isMysteryChestOpened) {
        this.isMysteryChestOpened = isMysteryChestOpened;
    }

    /**
     * Получает значение, открыт ли мистический сундук
     * @return Значение, открыт ли мистический сундук
     */
    public boolean isMysteryChestOpened() {
        return isMysteryChestOpened;
    }
}