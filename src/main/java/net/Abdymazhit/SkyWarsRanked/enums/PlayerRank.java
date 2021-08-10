package net.Abdymazhit.SkyWarsRanked.enums;

/**
 * Представляет собой ранг игрока
 *
 * @version   10.08.2021
 * @author    Islam Abdymazhit
 */
public enum PlayerRank {
    PLAYER("", "Игрок", null),
    VIP("§a", "VIP", "V"),
    PREMIUM("§b", "Premium", "P"),
    HOLY("§6", "Holy", "H"),
    IMMORTAL("§d", "Immortal", "I"),
    BUILDER("§2", "Билдер", "Билдер"),
    SRBUILDER("§2", "Проверенный билдер", "Пр. билдер"),
    MAPLEAD("§2", "Главный билдер", "Гл. билдер"),
    YOUTUBE("§c", "You§cTube", "§cYou§fTube"),
    DEV("§3", "Разработчик", "Dev"),
    ORGANIZER("§3", "Организатор", "Организатор"),
    MODER("§9", "Модератор", "Модер"),
    WARDEN("§9", "Проверенный модератор", "Модер"),
    CHIEF("§9", "Главный модератор", "Гл. модер"),
    ADMIN("§3§l", "Главный админ", "Гл. админ");

    private final String color;
    private final String name;
    private final String prefix;

    /**
     * Инициализирует ранг
     * @param color Цвет ранга
     * @param name Название ранга
     * @param prefix Префикс ранга
     */
    PlayerRank(String color, String name, String prefix) {
        this.color = color;
        this.name = ((name == null) ? "" : name);
        this.prefix = ((prefix == null) ? "" : prefix);
    }

    /**
     * Получает название ранга
     * @return Название ранга
     */
    public String getName() {
        return name;
    }

    /**
     * Получает префикс ранга
     * @return Префикс ранга
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Получает цвет ранга
     * @return Цвет ранга
     */
    public String getColor() {
        return color;
    }

    /**
     * Получает отображаемое имя
     * @return Отображаемое имя
     */
    public String getDisplayName() {
        return color + name + "§r";
    }
}