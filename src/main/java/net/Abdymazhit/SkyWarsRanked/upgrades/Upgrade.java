package net.Abdymazhit.SkyWarsRanked.upgrades;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

/**
 * Отвечает за прокачки
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public class Upgrade {

    /** Хранит информацию о прокачках по id */
    private static final Map<Integer, Upgrade> byId = new HashMap<>();

    /** Прокачка Пылающие стрелы */
    public static final Upgrade BLAZING_ARROWS = new BlazingArrows(1);

    /** Id прокачки */
    private final int id;

    /** Материал для показа в меню прокачки */
    private final Material material;

    /** Название прокачки */
    private final String name;

    /** Редкость прокачки */
    private final Rarity rarity;

    /** Описание прокачки */
    private final List<String> description;

    /** Хранит информацию о уровнях прокачки и улучшениях */
    private final Map<Integer, Integer> levelUpgrade;

    /**
     * Инициализирует прокачку
     * @param id Id прокачки
     * @param material Материал для показа в меню прокачки
     * @param name Название прокачки
     * @param rarity Редкость прокачки
     * @param description Описание прокачки
     * @param levelUpgrade Информация о уровнях прокачки и улучшениях
     */
    public Upgrade(int id, Material material, String name, Rarity rarity, List<String> description, Map<Integer, Integer> levelUpgrade) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.levelUpgrade = levelUpgrade;
    }

    /**
     * Региструет прокачку
     * @param upgrade Прокачка
     */
    public void registerUpgrade(Upgrade upgrade) {
        byId.put(upgrade.id, upgrade);
    }

    /**
     * Получает id прокачки
     * @return Id прокачки
     */
    public int getId() {
        return id;
    }

    /**
     * Получает материал прокачки
     * @return Материал прокачки
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Получает название прокачки
     * @return Название прокачки
     */
    public String getName() {
        return name;
    }

    /**
     * Получает редкость прокачки
     * @return Редкость прокачки
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Получает описание прокачки
     * @return Описание прокачки
     */
    public List<String> getDescription() {
        return description;
    }

    /**
     * Получает информацию о уровнях прокачки и улучшениях
     * @return Информация о уровнях прокачки и улучшениях
     */
    public Map<Integer, Integer> getLevelUpgrade() {
        return levelUpgrade;
    }

    /**
     * Возвращает значение, можно ли выполнить действия прокачки игрока
     * @param player Игрок
     * @param upgrade Прокачка
     * @return Значение, можно ли выполнить действия прокачки игрока
     */
    public static boolean performUpgradeAction(Player player, Upgrade upgrade) {
        Map<Upgrade, Integer> upgrades = SkyWarsRanked.getGameManager().getPlayerInfo(player).getUpgrades();
        if(upgrades.containsKey(Upgrade.BLAZING_ARROWS)) {
            SplittableRandom random = new SplittableRandom();
            return random.nextInt(1, 101) <= upgrades.get(Upgrade.BLAZING_ARROWS);
        } else {
            return false;
        }
    }

    /**
     * Получает прокачку по id
     * @param id Id прокачки
     * @return Прокачка
     */
    public static Upgrade getById(int id) {
        return byId.get(id);
    }

    /**
     * Получает список прокачек
     * @return Список прокачек
     */
    public static Upgrade[] values() {
        return byId.values().toArray(new Upgrade[byId.size()]);
    }
}