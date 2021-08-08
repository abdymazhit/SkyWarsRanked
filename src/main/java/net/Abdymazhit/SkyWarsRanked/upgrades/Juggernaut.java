package net.Abdymazhit.SkyWarsRanked.upgrades;

import net.Abdymazhit.SkyWarsRanked.enums.Rarity;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Представляет собой прокачку Джаггернаут
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public class Juggernaut extends Upgrade {

    /** Материал для показа в меню прокачки */
    private static final Material material = Material.DIAMOND_SWORD;

    /** Название прокачки */
    private static final String name = "§bДжаггернаут";

    /** Редкость прокачки */
    private static final Rarity rarity = Rarity.EPIC;

    /** Описание прокачки */
    private static final List<String> description = Arrays.asList(
            "§7После убийства игрока вы получите",
            "§7эффект Регенерации I на определенное",
            "§7количество секунд.",
            "",
            "§7Ваши текущие секунды: §f<upgrade>"
    );

    /** Хранит информацию о улучшениях уровней прокачки */
    private static final Map<Integer, Integer> levelsImprovement = new HashMap<Integer, Integer>() {{
        put(1, 1); // 1 уровень - 1 секунда
        put(2, 2);
        put(3, 3);
        put(4, 4);
        put(5, 5);
        put(6, 6);
        put(7, 7);
        put(8, 8);
        put(9, 9);
        put(10, 10); // 10 - 10 секунд
    }};

    /**
     * Инициализирует прокачку
     * @param id Id прокачки
     */
    public Juggernaut(int id) {
        super(id, material, name, rarity, description, levelsImprovement);
        registerUpgrade(this);
    }
}