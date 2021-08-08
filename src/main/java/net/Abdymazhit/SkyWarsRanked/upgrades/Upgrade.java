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

    /** Прокачка Джаггернаут */
    public static final Upgrade JUGGERNAUT = new Juggernaut(2);

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

    /** Хранит информацию о улучшениях уровней прокачки */
    private final Map<Integer, Integer> levelsImprovement;

    /**
     * Инициализирует прокачку
     * @param id Id прокачки
     * @param material Материал для показа в меню прокачки
     * @param name Название прокачки
     * @param rarity Редкость прокачки
     * @param description Описание прокачки
     * @param levelsImprovement Информация о улучшениях уровней прокачки
     */
    public Upgrade(int id, Material material, String name, Rarity rarity, List<String> description, Map<Integer, Integer> levelsImprovement) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.levelsImprovement = levelsImprovement;
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
     * Получает информацию о улучшениях уровней прокачки
     * @return Информация о улучшениях уровней прокачки
     */
    public Map<Integer, Integer> getLevelsImprovement() {
        return levelsImprovement;
    }

    /**
     * Возвращает значение, можно ли выполнить действия прокачки, которая работает по принципу шансов
     *
     * Прокачка Пылающие стрелы - работает по принципу шансов, так как стрела игрока может с определенным шансом стать горящей
     * Прокачка Джаггернаут - не работает, так как игрок после убийства получает эффект Регенерации на определенное количество секунд, здесь нету шансов
     *
     * @param player Игрок
     * @param upgrade Прокачка
     * @return Значение, можно ли выполнить действия прокачки, которая работает по принципу шансов
     */
    public static boolean canPerformUpgradeAction(Player player, Upgrade upgrade) {
        Map<Upgrade, Integer> upgrades = SkyWarsRanked.getGameManager().getPlayerInfo(player).getUpgrades();
        if(upgrades.containsKey(upgrade)) {
            SplittableRandom random = new SplittableRandom();
            return random.nextInt(1, 101) <= upgrades.get(upgrade);
        } else {
            return false;
        }
    }

    /**
     * Получает улучшение прокачки игрока
     * @param player Игрок
     * @param upgrade Прокачка
     * @return Улучшение прокачки игрока
     */
    public static int getPlayerUpgradeImprovement(Player player, Upgrade upgrade) {
        Map<Upgrade, Integer> upgrades = SkyWarsRanked.getGameManager().getPlayerInfo(player).getUpgrades();
        return upgrades.getOrDefault(upgrade, 0);
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