package net.Abdymazhit.SkyWarsRanked.kits;

import net.Abdymazhit.SkyWarsRanked.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Представляет собой набор Чародей
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class Enchanter extends Kit {

    /** Материал для показа в меню набора */
    private static final Material material = Material.ENCHANTMENT_TABLE;

    /** Название набора */
    private static final String name = "Чародей";

    /** Редкость набора */
    private static final Rarity rarity = Rarity.COMMON;

    /** Хранит информацию о описании уровней набора */
    private static final Map<Integer, List<String>> levelDescriptions = new HashMap<Integer, List<String>>() {{
        put(1, Collections.singletonList(
                "§7Пузырёк опыта §8x8"
        ));
        put(2, Collections.singletonList(
                "§7Пузырёк опыта §8x16"
        ));
        put(3, Collections.singletonList(
                "§7Пузырёк опыта §8x24"
        ));
        put(4, Collections.singletonList(
                "§7Пузырёк опыта §8x32"
        ));
        put(5, Arrays.asList(
                "§7Пузырёк опыта §8x32",
                "§7Стол зачарования §8x1"
        ));
        put(6, Arrays.asList(
                "§7Пузырёк опыта §8x40",
                "§7Стол зачарования §8x1"
        ));
        put(7, Arrays.asList(
                "§7Пузырёк опыта §8x48",
                "§7Стол зачарования §8x1"
        ));
        put(8, Arrays.asList(
                "§7Пузырёк опыта §8x56",
                "§7Стол зачарования §8x1"
        ));
        put(9, Arrays.asList(
                "§7- Пузырёк опыта §8x64",
                "§7Стол зачарования §8x1"
        ));
        put(10, Arrays.asList(
                "§7Пузырёк опыта §8x128",
                "§7Стол зачарования §8x1"
        ));
    }};

    /** Хранит информацию о предметах уровней набора */
    private static final Map<Integer, ItemStack[]> levelItems = new HashMap<Integer, ItemStack[]>() {{
        put(1, new ItemStack[] { new ItemStack(Material.EXP_BOTTLE, 8) });
        put(2, new ItemStack[] { new ItemStack(Material.EXP_BOTTLE, 16) });
        put(3, new ItemStack[] { new ItemStack(Material.EXP_BOTTLE, 24) });
        put(4, new ItemStack[] { new ItemStack(Material.EXP_BOTTLE, 32) });
        put(5, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 32) });
        put(6, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 40) });
        put(7, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 48) });
        put(8, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 56) });
        put(9, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 64) });
        put(10, new ItemStack[] { new ItemStack(Material.ENCHANTMENT_TABLE), new ItemStack(Material.EXP_BOTTLE, 128) });
    }};

    /**
     * Инициализирует набор
     * @param id Id набора
     */
    public Enchanter(int id) {
        super(id, material, name, rarity, levelDescriptions, levelItems);
    }
}