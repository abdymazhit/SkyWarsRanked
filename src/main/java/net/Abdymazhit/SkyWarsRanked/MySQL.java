package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.kits.Kit;
import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Отвечает за работу с базой данных
 *
 * @version   14.08.2021
 * @author    Islam Abdymazhit
 */
public class MySQL {

    /**
     * Получает и устанавливает выбранный набор игрока
     * @param player Игрок
     */
    public void getPlayerKit(Player player) {
        SkyWarsRanked.getGameManager().getPlayerInfo(player).setKit(Kit.ENCHANTER);
    }

    /**
     * Получает и устанавливает доступные наборы игрока с их уровнями
     * @param player Игрок
     */
    public void getPlayerKits(Player player) {
        Map<Kit, Integer> playerKits = new HashMap<>();
        playerKits.put(Kit.ENCHANTER, 10);

        SkyWarsRanked.getGameManager().getPlayerInfo(player).setKits(playerKits);
        SkyWarsRanked.getGameManager().getGameItems().addPlayerKitSelectMenu(player);
    }

    /**
     * Получает и устанавливает общий рейтинг игрока
     * @param player Игрок
     */
    public void getPlayerOverallRating(Player player) {
        SkyWarsRanked.getGameManager().getPlayerInfo(player).setOverallRating(234);
    }

    /**
     * Получает и устанавливает рейтинги игрока за каждый набор
     * @param player Игрок
     */
    public void getPlayerKitsRatings(Player player) {
        Map<Kit, Integer> kitsRatings = new HashMap<>();
        kitsRatings.put(Kit.ENCHANTER, 234);
        SkyWarsRanked.getGameManager().getPlayerInfo(player).setKitsRatings(kitsRatings);
    }

    /**
     * Получает и устанавливает доступные прокачки игрока с их уровнями
     * @param player Игрок
     */
    public void getPlayerUpgrades(Player player) {
        Map<Upgrade, Integer> playerUpgrades = new HashMap<>();
        playerUpgrades.put(Upgrade.BLAZING_ARROWS, 10);
        playerUpgrades.put(Upgrade.JUGGERNAUT, 10);

        SkyWarsRanked.getGameManager().getPlayerInfo(player).setUpgrades(playerUpgrades);
        SkyWarsRanked.getGameManager().getGameItems().addPlayerUpgradesMenu(player);
    }

    /**
     * Добавить коины игроку
     * @param player Игрок
     * @param coins Коины
     */
    public void addCoins(Player player, int coins) {
        player.sendMessage("§eДобавлено коинов: " + coins);
    }

    /**
     * Добавить опыт игроку
     * @param player Игрок
     * @param exp Опыт
     */
    public void giveExp(Player player, int exp) {
        player.sendMessage("§9Добавлено опыта: " + exp);
    }
}