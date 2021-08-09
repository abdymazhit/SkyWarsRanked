package net.Abdymazhit.SkyWarsRanked.kits;

import net.Abdymazhit.SkyWarsRanked.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Отвечает за наборы
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class Kit {

    /** Хранит наборы по id */
    private static final Map<Integer, Kit> byId = new HashMap<>();

    /** Набор чародей */
    public static final Kit ENCHANTER = new Enchanter(1);

    /** Id набора */
    private final int id;

    /** Материал для показа в меню набора */
    private final Material material;

    /** Название набора */
    private final String name;

    /** Редкость набора */
    private final Rarity rarity;

    /** Описания уровней набора */
    private final Map<Integer, List<String>> levelDescriptions;

    /** Предметы уровней набора */
    private final Map<Integer, ItemStack[]> levelItems;

    /**
     * Инициализирует набор
     * @param id Id набора
     * @param material Материал для показа в меню набора
     * @param name Название набора
     * @param rarity Редкость набора
     * @param levelDescriptions Описания уровней набора
     * @param levelItems Предметы уровней набора
     */
    public Kit(int id, Material material, String name, Rarity rarity, Map<Integer, List<String>> levelDescriptions, Map<Integer, ItemStack[]> levelItems) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.levelDescriptions = levelDescriptions;
        this.levelItems = levelItems;
        byId.put(id, this);
    }

    /**
     * Получает id набора
     * @return Id набора
     */
    public int getId() {
        return id;
    }

    /**
     * Получает материал набора
     * @return Материал набора
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Получает название набора
     * @return Название набора
     */
    public String getName() {
        return name;
    }

    /**
     * Получает редкость набора
     * @return Редкость набора
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Получает описания уровней набора
     * @return Описания уровней набора
     */
    public Map<Integer, List<String>> getLevelDescriptions() {
        return levelDescriptions;
    }

    /**
     * Получает предметы уровней набора
     * @return Предметы уровней набора
     */
    public Map<Integer, ItemStack[]> getLevelItems() {
        return levelItems;
    }

    /**
     * Получает набор по id
     * @param id Id набора
     * @return Набор
     */
    public static Kit getById(int id) {
        return byId.get(id);
    }

    /**
     * Получает массив наборов
     * @return Массив наборов
     */
    public static Kit[] values() {
        return byId.values().toArray(new Kit[0]);
    }
}