package net.Abdymazhit.SkyWarsRanked.customs;

import net.Abdymazhit.SkyWarsRanked.enums.PlayerRank;

/**
 * Представляет собой глобальную информацию о игроке
 *
 * @version   07.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerVimeInfo {

    /** Id игрока */
    private final int id;

    /** Ранг игрока */
    private final PlayerRank rank;

    /** Тег гильдии игрока */
    private final String guildTag;

    /** Цвет тега гильдии игрока */
    private final String guildColor;

    /**
     * Создает глобальную информацию о игроке
     * @param id Id игрока
     * @param rank Ранг игрока
     * @param guildTag Тег гильдии игрока
     * @param guildColor Цвет тега гильдии игрока
     */
    public PlayerVimeInfo(int id, PlayerRank rank, String guildTag, String guildColor) {
        this.id = id;
        this.rank = rank;
        this.guildTag = guildTag;
        this.guildColor = guildColor;
    }

    /**
     * Получает id игрока
     * @return Id игрока
     */
    public int getId() {
        return id;
    }

    /**
     * Получает ранг игрока
     * @return Ранг игрока
     */
    public PlayerRank getRank() {
        return rank;
    }

    /**
     * Получает тег гильдии игрока
     * @return Тег гильдии игрока
     */
    public String getGuildTag() {
        return guildTag;
    }

    /**
     * Получает цвет тега гильдии игрока
     * @return Цвет тега гильдии игрока
     */
    public String getGuildColor() {
        return guildColor;
    }
}