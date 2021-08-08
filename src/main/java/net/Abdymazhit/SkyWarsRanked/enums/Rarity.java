package net.Abdymazhit.SkyWarsRanked.enums;

/**
 * Представляет собой редкость
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public enum Rarity {
    COMMON,
    RARE,
    EPIC,
    LEGENDARY;

    /**
     * Получает название редкости
     * @return Название редкости
     */
    public String getName() {
        switch (this) {
            case COMMON:
                return "§aОБЫЧНЫЙ";
            case RARE:
                return "§9РЕДКИЙ";
            case EPIC:
                return "§5ЭПИЧЕСКИЙ";
            case LEGENDARY:
                return "§6ЛЕГЕНДАРНЫЙ";
            default:
                return "§cОШИБКА";
        }
    }
}