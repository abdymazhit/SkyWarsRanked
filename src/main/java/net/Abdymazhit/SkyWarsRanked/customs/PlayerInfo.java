package net.Abdymazhit.SkyWarsRanked.customs;

/**
 * Представляет собой информацию о игроке
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerInfo {

    /** Глобальная информация о игроке */
    private final PlayerVimeInfo playerVimeInfo;

    /** Информация о статистике игрока */
    private final PlayerStats playerStats;

    /** Количество убийств игрока в этом матче */
    private int kills;

    /** Количество выпущенных стрел игрока в этом матче */
    private int arrowsFired;

    /** Количество сломанных блоков игрока в этом матче */
    private int blocksBroken;

    /** Количество поставленных блоков игрока в этом матче */
    private int blocksPlaced;

    /**
     * Создает информацию о игроке
     * @param playerVimeInfo Глобальная информация о игроке
     * @param playerStats Информация о статистике игрока
     */
    public PlayerInfo(PlayerVimeInfo playerVimeInfo, PlayerStats playerStats) {
        this.playerVimeInfo = playerVimeInfo;
        this.playerStats = playerStats;
        this.kills = 0;
        arrowsFired = 0;
        blocksBroken = 0;
        blocksPlaced = 0;
    }

    /**
     * Получает глобальную информацию о игроке
     * @return Глобальная информация о игроке
     */
    public PlayerVimeInfo getPlayerVimeInfo() {
        return playerVimeInfo;
    }

    /**
     * Получает информацию о статистике игрока
     * @return Информация о статистике игрока
     */
    public PlayerStats getPlayerStats() {
        return playerStats;
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
}