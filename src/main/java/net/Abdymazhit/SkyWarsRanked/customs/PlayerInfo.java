package net.Abdymazhit.SkyWarsRanked.customs;

import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Представляет собой информацию о игроке
 *
 * @version   07.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerInfo {

    /** Глобальная информация о игроке */
    private final PlayerVimeInfo playerVimeInfo;

    /** Статистика игрока */
    private final Stats stats;

    /** Хранит информацию о прокачках игрока с их уровнем */
    private final Map<Upgrade, Integer> upgrades;

    /** Количество убийств игрока в этом матче */
    private int kills;

    /** Количество выпущенных стрел игрока в этом матче */
    private int arrowsFired;

    /** Количество сломанных блоков игрока в этом матче */
    private int blocksBroken;

    /** Количество поставленных блоков игрока в этом матче */
    private int blocksPlaced;

    /** Последний нанесший урон по игроку */
    private Player lastDamager;

    /** Местоположение смерти игрока */
    private Location deathLocation;

    /**
     * Создает информацию о игроке
     * @param playerVimeInfo Глобальная информация о игроке
     * @param stats Статистика игрока
     * @param upgrades Информация о прокачках игрока с их уровнем
     */
    public PlayerInfo(PlayerVimeInfo playerVimeInfo, Stats stats, Map<Upgrade, Integer> upgrades) {
        this.playerVimeInfo = playerVimeInfo;
        this.stats = stats;
        this.upgrades = upgrades;
        this.kills = 0;
        this.arrowsFired = 0;
        this.blocksBroken = 0;
        this.blocksPlaced = 0;
        this.lastDamager = null;
        this.deathLocation = null;
    }

    /**
     * Получает глобальную информацию о игроке
     * @return Глобальная информация о игроке
     */
    public PlayerVimeInfo getPlayerVimeInfo() {
        return playerVimeInfo;
    }

    /**
     * Получает статистику игрока
     * @return Статистика игрока
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Получает информацию о прокачках игрока с их уровнем
     * @return Информация о прокачках игрока с их уровнем
     */
    public Map<Upgrade, Integer> getUpgrades() {
        return upgrades;
    }

    /**
     * Увеличивает количество убийств игрока в этом матче
     */
    public void addKills() {
        kills++;
    }

    /**
     * Получает количество убийств игрока в этом матче
     * @return Количество убийств игрока в этом матче
     */
    public int getKills() {
        return kills;
    }

    /**
     * Увеличивает количество выпущенных стрел игрока в этом матче
     */
    public void addArrowsFired() {
        arrowsFired++;
    }

    /**
     * Получает количество выпущенных стрел игрока в этом матче
     * @return Количество выпущенных стрел игрока в этом матче
     */
    public int getArrowsFired() {
        return arrowsFired;
    }

    /**
     * Увеличивает количество сломанных блоков игрока в этом матче
     */
    public void addBlocksBroken() {
        blocksBroken++;
    }

    /**
     * Получает количество сломанных блоков игрока в этом матче
     * @return Количество сломанных блоков игрока в этом матче
     */
    public int getBlocksBroken() {
        return blocksBroken;
    }

    /**
     * Увеличивает количество поставленных блоков игрока в этом матче
     */
    public void addBlocksPlaced() {
        blocksPlaced++;
    }

    /**
     * Получает количество поставленных блоков игрока в этом матче
     * @return Количество поставленных блоков игрока в этом матче
     */
    public int getBlocksPlaced() {
        return blocksPlaced;
    }

    /**
     * Установить последнего нанесшего урон по игроку
     * @param lastDamager Последний нанесший урон по игроку
     */
    public void setLastDamager(Player lastDamager) {
        this.lastDamager = lastDamager;
    }

    /**
     * Получает последнего нанесшего урон по игроку
     * @return Последний нанесший урон по игроку
     */
    public Player getLastDamager() {
        return lastDamager;
    }

    /**
     * Устанавливает местоположение смерти игрока
     * @param deathLocation Местоположение смерти игрока
     */
    public void setDeathLocation(Location deathLocation) {
        this.deathLocation = deathLocation;
    }

    /**
     * Получает местоположение смерти игрока
     * @return Местоположение смерти игрока
     */
    public Location getDeathLocation() {
        return deathLocation;
    }
}