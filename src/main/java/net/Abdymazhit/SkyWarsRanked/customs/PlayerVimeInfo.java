package net.Abdymazhit.SkyWarsRanked.customs;

import net.Abdymazhit.SkyWarsRanked.enums.Rank;

/**
 * Представляет собой глобальную информацию о игроке
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerVimeInfo {

    /** Id игрока */
    private final int id;

    /** Ранг игрока */
    private final Rank rank;

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
    public PlayerVimeInfo(int id, Rank rank, String guildTag, String guildColor) {
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
    public Rank getRank() {
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