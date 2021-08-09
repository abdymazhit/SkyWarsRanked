package net.Abdymazhit.SkyWarsRanked.enums;

/**
 * Представляет собой ранг игрока
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public enum PlayerRank {
    PLAYER,
    VIP,
    PREMIUM,
    HOLY,
    IMMORTAL,
    BUILDER,
    SRBUILDER,
    MAPLEAD,
    YOUTUBE,
    DEV,
    ORGANIZER,
    MODER,
    WARDEN,
    CHIEF,
    ADMIN;

    /**
     * Получает префикс ранга игрока
     * @return Префикс ранга игрока
     */
    public String getPrefix() {
        switch (this) {
            case PLAYER:
                return "§f";
            case VIP:
                return "§a[V]";
            case PREMIUM:
                return "§b[P]";
            case HOLY:
                return "§6[H]";
            case IMMORTAL:
                return "§5[I]";
            case BUILDER:
                return "§2[Билдер]";
            case SRBUILDER:
                return "§2[Пр. билдер]";
            case MAPLEAD:
                return "§2[Гл. билдер]";
            case YOUTUBE:
                return "§c[YouTube]";
            case DEV:
                return "§b[Dev]";
            case ORGANIZER:
                return "§b[Организатор]";
            case MODER:
            case WARDEN:
                return "§9[Модер]";
            case CHIEF:
                return "§9[Гл. модер]";
            case ADMIN:
                return "§b[Гл. админ]";
            default:
                return "§cОШИБКА";
        }
    }

    /**
     * Получает цвет ранга игрока
     * @return Цвет ранга игрока
     */
    public String getColor() {
        String prefix = getPrefix();
        return prefix.substring(0, 1);
    }
}