package net.Abdymazhit.SkyWarsRanked;

import net.Abdymazhit.SkyWarsRanked.kits.Kit;
import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Отвечает за работу с базой данных
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class MySQL {

    /**
     * Получает и устанавливает выбранный набор игрока
     */
    public void getPlayerKit(Player player) {
        SkyWarsRanked.getGameManager().getPlayerInfo(player).setKit(Kit.ENCHANTER);
    }

    /**
     * Получает и устанавливает доступные наборы игрока с их уровнями
     */
    public void getPlayerKits(Player player) {
        Map<Kit, Integer> playerKits = new HashMap<>();
        playerKits.put(Kit.ENCHANTER, 10);

        SkyWarsRanked.getGameManager().getPlayerInfo(player).setKits(playerKits);
        SkyWarsRanked.getGameItems().addPlayerKitSelectMenu(player);
    }

    /**
     * Получает и устанавливает доступные прокачки игрока с их уровнями
     */
    public void getPlayerUpgrades(Player player) {
        Map<Upgrade, Integer> playerUpgrades = new HashMap<>();
        playerUpgrades.put(Upgrade.BLAZING_ARROWS, 10);
        playerUpgrades.put(Upgrade.JUGGERNAUT, 10);

        SkyWarsRanked.getGameManager().getPlayerInfo(player).setUpgrades(playerUpgrades);
        SkyWarsRanked.getGameItems().addPlayerUpgradesMenu(player);
    }
}