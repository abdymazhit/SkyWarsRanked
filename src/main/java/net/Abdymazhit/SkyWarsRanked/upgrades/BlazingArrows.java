package net.Abdymazhit.SkyWarsRanked.upgrades;

import net.Abdymazhit.SkyWarsRanked.enums.Rarity;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Представляет собой прокачку Пылающие стрелы
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class BlazingArrows extends Upgrade {

    /** Материал для показа в меню прокачки */
    private static final Material material = Material.BLAZE_ROD;

    /** Название прокачки */
    private static final String name = "Пылающие стрелы";

    /** Редкость прокачки */
    private static final Rarity rarity = Rarity.RARE;

    /** Описание прокачки */
    private static final List<String> description = Arrays.asList(
            "§7При выстреле с определенным шансом",
            "§7ваша стрела будет горящей.",
            "",
            "§7Ваш текущий шанс: §f<upgrade>%"
    );

    /** Хранит информацию о улучшениях уровней прокачки */
    private static final Map<Integer, Integer> levelsImprovement = new HashMap<Integer, Integer>() {{
        put(1, 1); // 1 уровень - 1% шанс
        put(2, 2);
        put(3, 3);
        put(4, 4);
        put(5, 5);
        put(6, 6);
        put(7, 7);
        put(8, 8);
        put(9, 9);
        put(10, 10); // 10 уровень - 10% шанс
    }};

    /**
     * Инициализирует прокачку
     * @param id Id прокачки
     */
    public BlazingArrows(int id) {
        super(id, material, name, rarity, description, levelsImprovement);
    }
}