package net.Abdymazhit.SkyWarsRanked.customs;

/**
 * Представляет собой информацию о статистике игроке
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerStats {

    /** Общее количество побед игрока */
    private final int wins;

    /** Общее количество сыгранных игр игрока */
    private final int games;

    /** Общее количество убийств игрока */
    private final int kills;

    /** Общее количество смертей игрока */
    private final int deaths;

    /** Общее количество выпущенных стрел игрока */
    private final int arrowsFired;

    /** Общее количество сломанных блоков игрока */
    private final int blocksBroken;

    /** Общее количество поставленных блоков игрока */
    private final int blocksPlaced;

    /** Текущая серия побед игрока */
    private final int currentWinStreak;

    /** Максимальная серия побед игрока */
    private final int winStreak;

    /** Количество побед игрока за месяц */
    private final int monthlyWins;

    /** Количество сыгранных игр игрока за месяц */
    private final int monthlyGames;

    /** Количество убийств игрока за месяц */
    private final int monthlyKills;

    /** Количество смертей игрока за месяц */
    private final int monthlyDeaths;

    /** Количество выпущенных стрел игрока за месяц */
    private final int monthlyArrowsFired;

    /** Количество сломанных блоков игрока за месяц */
    private final int monthlyBlocksBroken;

    /** Количество поставленных блоков игрока за месяц */
    private final int monthlyBlocksPlaced;

    /**
     * Создает информацию о статистике игроке
     * @param wins Общее количество побед игрока
     * @param games Общее количество сыгранных игр игрока
     * @param kills Общее количество убийств игрока
     * @param deaths Общее количество смертей игрока
     * @param arrowsFired Общее количество выпущенных стрел игрока
     * @param blocksBroken Общее количество сломанных блоков игрока
     * @param blocksPlaced Общее количество поставленных блоков игрока
     * @param currentWinStreak Текущая серия побед игрока
     * @param winStreak Максимальная серия побед игрока
     * @param monthlyWins Количество побед игрока за месяц
     * @param monthlyGames Количество сыгранных игр игрока за месяц
     * @param monthlyKills Количество убийств игрока за месяц
     * @param monthlyDeaths Количество смертей игрока за месяц
     * @param monthlyArrowsFired Количество выпущенных стрел игрока за месяц
     * @param monthlyBlocksBroken Количество сломанных блоков игрока за месяц
     * @param monthlyBlocksPlaced Количество поставленных блоков игрока за месяц
     */
    public PlayerStats(int wins, int games, int kills, int deaths, int arrowsFired, int blocksBroken, int blocksPlaced, int currentWinStreak, int winStreak,
                       int monthlyWins, int monthlyGames, int monthlyKills, int monthlyDeaths, int monthlyArrowsFired, int monthlyBlocksBroken, int monthlyBlocksPlaced) {
        this.wins = wins;
        this.games = games;
        this.kills = kills;
        this.deaths = deaths;
        this.arrowsFired = arrowsFired;
        this.blocksBroken = blocksBroken;
        this.blocksPlaced = blocksPlaced;
        this.currentWinStreak = currentWinStreak;
        this.winStreak = winStreak;
        this.monthlyWins = monthlyWins;
        this.monthlyGames = monthlyGames;
        this.monthlyKills = monthlyKills;
        this.monthlyDeaths = monthlyDeaths;
        this.monthlyArrowsFired = monthlyArrowsFired;
        this.monthlyBlocksBroken = monthlyBlocksBroken;
        this.monthlyBlocksPlaced = monthlyBlocksPlaced;
    }

    /**
     * Получает общее количество побед игрока
     * @return Общее количество побед игрока
     */
    public int getWins() {
        return wins;
    }

    /**
     * Получает общее количество сыгранных игр игрока
     * @return Общее количество сыгранных игр игрока
     */
    public int getGames() {
        return games;
    }

    /**
     * Получает общее количество убийств игрока
     * @return Общее количество убийств игрока
     */
    public int getKills() {
        return kills;
    }

    /**
     * Получает общее количество смертей игрока
     * @return Общее количество смертей игрока
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Получает общее количество выпущенных стрел игрока
     * @return Общее количество выпущенных стрел игрока
     */
    public int getArrowsFired() {
        return arrowsFired;
    }

    /**
     * Получает общее количество сломанных блоков игрока
     * @return Общее количество сломанных блоков игрока
     */
    public int getBlocksBroken() {
        return blocksBroken;
    }

    /**
     * Получает общее количество поставленных блоков игрока
     * @return Общее количество поставленных блоков игрока
     */
    public int getBlocksPlaced() {
        return blocksPlaced;
    }

    /**
     * Получает текущую серию побед игрока
     * @return Текущая серия побед игрока
     */
    public int getCurrentWinStreak() {
        return currentWinStreak;
    }

    /**
     * Получает максимальную серию побед игрока
     * @return Максимальная серия побед игрока
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Получает количество побед игрока за месяц
     * @return Количество побед игрока за месяц
     */
    public int getMonthlyWins() {
        return monthlyWins;
    }

    /**
     * Получает количество сыгранных игр игрока за месяц
     * @return Количество сыгранных игр игрока за месяц
     */
    public int getMonthlyGames() {
        return monthlyGames;
    }

    /**
     * Получает количество убийств игрока за месяц
     * @return Количество убийств игрока за месяц
     */
    public int getMonthlyKills() {
        return monthlyKills;
    }

    /**
     * Получает количество смертей игрока за месяц
     * @return Количество смертей игрока за месяц
     */
    public int getMonthlyDeaths() {
        return monthlyDeaths;
    }

    /**
     * Получает количество выпущенных стрел игрока за месяц
     * @return Количество выпущенных стрел игрока за месяц
     */
    public int getMonthlyArrowsFired() {
        return monthlyArrowsFired;
    }

    /**
     * Получает количество сломанных блоков игрока за месяц
     * @return Количество сломанных блоков игрока за месяц
     */
    public int getMonthlyBlocksBroken() {
        return monthlyBlocksBroken;
    }

    /**
     * Получает количество поставленных блоков игрока за месяц
     * @return Количество поставленных блоков игрока за месяц
     */
    public int getMonthlyBlocksPlaced() {
        return monthlyBlocksPlaced;
    }
}