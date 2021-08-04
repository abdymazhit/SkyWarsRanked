package net.Abdymazhit.SkyWarsRanked.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

/**
 * Отвечает за игровые предметы
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class GameItems {

    /** Предмет зрителя, используется для телепортации к живым игрокам */
    private final ItemStack teleportItem;

    /** Предмет лобби, используется для открытия меню выбора острова */
    private final ItemStack islandsItem;

    /** Предмет лобби, используется для открытия меню прокачек */
    private final ItemStack upgradesItem;

    /** Предмет лобби, используется для открытия меню выбора набора */
    private final ItemStack kitsItem;

    /** Предмет лобби, используется для открытия меню космитики */
    private final ItemStack cosmeticsItem;

    /** Предмет лобби, используется для открытия меню выбора следа */
    private final ItemStack tracesItem;

    /** Глобальный предмет, используется для открытия меню игрока */
    private final ItemStack meItem;

    /** Глобальный предмет, используется для возвращения в главное лобби */
    private final ItemStack leaveItem;

    /**
     * Инициализирует игровые предметы
     */
    public GameItems() {
        List<String> lore = Collections.singletonList("§5§oVimeWorld.ru");

        teleportItem = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta teleportItemMeta = teleportItem.getItemMeta();
        teleportItemMeta.setDisplayName("§r>> §e§lТелепортация к игрокам §r<<");
        teleportItemMeta.setLore(lore);
        teleportItem.setItemMeta(teleportItemMeta);

        islandsItem = new ItemStack(Material.NAME_TAG);
        ItemMeta islandsItemMeta = islandsItem.getItemMeta();
        islandsItemMeta.setDisplayName("§r>> §e§lВыбор острова §r<<");
        islandsItemMeta.setLore(lore);
        islandsItem.setItemMeta(islandsItemMeta);

        upgradesItem = new ItemStack(Material.EYE_OF_ENDER);
        ItemMeta upgradesItemMeta = upgradesItem.getItemMeta();
        upgradesItemMeta.setDisplayName("§r>> §e§lМеню прокачек §r<<");
        upgradesItemMeta.setLore(lore);
        upgradesItem.setItemMeta(upgradesItemMeta);

        kitsItem = new ItemStack(Material.BOW);
        ItemMeta kitsItemMeta = kitsItem.getItemMeta();
        kitsItemMeta.setDisplayName("§r>> §e§lВыбор стартового набора §r<<");
        kitsItemMeta.setLore(lore);
        kitsItem.setItemMeta(kitsItemMeta);

        cosmeticsItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta cosmeticsItemMeta = cosmeticsItem.getItemMeta();
        cosmeticsItemMeta.setDisplayName("§r>> §e§lКосметика §r<<");
        cosmeticsItemMeta.setLore(lore);
        cosmeticsItem.setItemMeta(cosmeticsItemMeta);

        tracesItem = new ItemStack(Material.INK_SACK, 1, (byte) 11);
        ItemMeta tracesItemMeta = tracesItem.getItemMeta();
        tracesItemMeta.setDisplayName("§r>> §e§lВыбор следа §r<<");
        tracesItemMeta.setLore(lore);
        tracesItem.setItemMeta(tracesItemMeta);

        meItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setLore(lore);
        meItem.setItemMeta(meItemMeta);

        leaveItem = new ItemStack(Material.COMPASS);
        ItemMeta leaveItemMeta = leaveItem.getItemMeta();
        leaveItemMeta.setDisplayName("§r>> §e§lВернуться в лобби §r<<");
        leaveItemMeta.setLore(lore);
        leaveItem.setItemMeta(leaveItemMeta);
    }

    /**
     * Выдает игроку предметы лобби
     * @param player Игрок
     */
    public void giveLobbyItems(Player player) {
        player.getInventory().clear();

        player.getInventory().setItem(0, islandsItem);
        player.getInventory().setItem(1, upgradesItem);
        player.getInventory().setItem(2, kitsItem);
        player.getInventory().setItem(3, cosmeticsItem);
        player.getInventory().setItem(6, tracesItem);
        player.getInventory().setItem(7, meItem);
        player.getInventory().setItem(8, leaveItem);

        ItemStack meItem = player.getInventory().getItem(7);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setDisplayName("§r>> §e§l" + player.getName() + " §r<<");
        meItem.setItemMeta(meItemMeta);
    }

    /**
     * Выдает зрителю предметы зрителя
     * @param player Зритель
     */
    public void giveSpectatorItems(Player player) {
        player.getInventory().clear();

        player.getInventory().setItem(0, teleportItem);
        player.getInventory().setItem(7, meItem);
        player.getInventory().setItem(8, leaveItem);

        ItemStack meItem = player.getInventory().getItem(7);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setDisplayName("§r>> §e§l" + player.getName() + " §r<<");
        meItem.setItemMeta(meItemMeta);
    }
}