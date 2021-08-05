package net.Abdymazhit.SkyWarsRanked.items;

import net.Abdymazhit.SkyWarsRanked.items.menu.IslandSelectMenu;
import net.Abdymazhit.SkyWarsRanked.items.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Отвечает за игровые предметы
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class GameItems {

    /** Хранит предметы лобби с их слотом */
    private final Map<ItemStack, Integer> lobbyItems;

    /** Хранит предметы зрителя с их слотом */
    private final Map<ItemStack, Integer> spectatorItems;

    /** Хранит функцию использования предмета */
    private final Map<ItemStack, ItemUsage> itemUsage;

    /** Хранит информацию о меню предмета */
    private final Map<ItemStack, Menu> itemMenu;

    /**
     * Инициализирует игровые предметы
     */
    public GameItems() {
        lobbyItems = new HashMap<>();
        spectatorItems = new HashMap<>();
        itemUsage = new HashMap<>();
        itemMenu = new HashMap<>();

        List<String> lore = Collections.singletonList("§5§oVimeWorld.ru");

        ItemStack teleportItem = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta teleportItemMeta = teleportItem.getItemMeta();
        teleportItemMeta.setDisplayName("§r>> §e§lТелепортация к игрокам §r<<");
        teleportItemMeta.setLore(lore);
        teleportItem.setItemMeta(teleportItemMeta);
        spectatorItems.put(teleportItem, 0);

        ItemStack islandsItem = new ItemStack(Material.NAME_TAG);
        ItemMeta islandsItemMeta = islandsItem.getItemMeta();
        islandsItemMeta.setDisplayName("§r>> §e§lВыбор острова §r<<");
        islandsItemMeta.setLore(lore);
        islandsItem.setItemMeta(islandsItemMeta);
        lobbyItems.put(islandsItem, 0);
        itemMenu.put(islandsItem, new IslandSelectMenu());
        itemUsage.put(islandsItem, player -> itemMenu.get(islandsItem).open(player));

        ItemStack upgradesItem = new ItemStack(Material.EYE_OF_ENDER);
        ItemMeta upgradesItemMeta = upgradesItem.getItemMeta();
        upgradesItemMeta.setDisplayName("§r>> §e§lМеню прокачек §r<<");
        upgradesItemMeta.setLore(lore);
        upgradesItem.setItemMeta(upgradesItemMeta);
        lobbyItems.put(upgradesItem, 1);

        ItemStack kitsItem = new ItemStack(Material.BOW);
        ItemMeta kitsItemMeta = kitsItem.getItemMeta();
        kitsItemMeta.setDisplayName("§r>> §e§lВыбор стартового набора §r<<");
        kitsItemMeta.setLore(lore);
        kitsItem.setItemMeta(kitsItemMeta);
        lobbyItems.put(kitsItem, 2);

        ItemStack cosmeticsItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta cosmeticsItemMeta = cosmeticsItem.getItemMeta();
        cosmeticsItemMeta.setDisplayName("§r>> §e§lКосметика §r<<");
        cosmeticsItemMeta.setLore(lore);
        cosmeticsItem.setItemMeta(cosmeticsItemMeta);
        lobbyItems.put(cosmeticsItem, 3);

        ItemStack tracesItem = new ItemStack(Material.INK_SACK, 1, (byte) 11);
        ItemMeta tracesItemMeta = tracesItem.getItemMeta();
        tracesItemMeta.setDisplayName("§r>> §e§lВыбор следа §r<<");
        tracesItemMeta.setLore(lore);
        tracesItem.setItemMeta(tracesItemMeta);
        lobbyItems.put(tracesItem, 6);

        ItemStack meItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setLore(lore);
        meItem.setItemMeta(meItemMeta);
        lobbyItems.put(meItem, 7);
        spectatorItems.put(meItem, 7);

        ItemStack leaveItem = new ItemStack(Material.COMPASS);
        ItemMeta leaveItemMeta = leaveItem.getItemMeta();
        leaveItemMeta.setDisplayName("§r>> §e§lВернуться в лобби §r<<");
        leaveItemMeta.setLore(lore);
        leaveItem.setItemMeta(leaveItemMeta);
        lobbyItems.put(leaveItem, 8);
        spectatorItems.put(leaveItem, 8);
    }

    /**
     * Выдает игроку предметы лобби
     * @param player Игрок
     */
    public void giveLobbyItems(Player player) {
        player.getInventory().clear();

        for(ItemStack itemStack : lobbyItems.keySet()) {
            int slot = lobbyItems.get(itemStack);
            player.getInventory().setItem(slot, itemStack);
        }

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

        for(ItemStack itemStack : spectatorItems.keySet()) {
            int slot = spectatorItems.get(itemStack);
            player.getInventory().setItem(slot, itemStack);
        }

        ItemStack meItem = player.getInventory().getItem(7);
        ItemMeta meItemMeta = meItem.getItemMeta();
        meItemMeta.setDisplayName("§r>> §e§l" + player.getName() + " §r<<");
        meItem.setItemMeta(meItemMeta);
    }

    /**
     * Обрабатывает событие использования предмета
     * @param player Игрок
     * @param itemStack Предмет
     */
    public void useItem(Player player, ItemStack itemStack) {
        if(itemUsage.get(itemStack) != null) {
            itemUsage.get(itemStack).use(player);
        }
    }

    /**
     * Обрабатывает событие клика по инвентарю
     * @param player Игрок
     * @param inventory Инвентарь
     */
    public void clickInventory(Player player, Inventory inventory, int slot) {
        for(ItemStack itemStack : itemMenu.keySet()) {
            Menu menu = itemMenu.get(itemStack);
            if(menu.getInventory().equals(inventory)) {
                menu.clickSlot(player, slot);
            }
        }
    }
}