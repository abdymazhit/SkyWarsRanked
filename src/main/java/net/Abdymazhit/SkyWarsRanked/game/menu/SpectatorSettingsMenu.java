package net.Abdymazhit.SkyWarsRanked.game.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Меню настроек зрителя
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class SpectatorSettingsMenu extends Menu {

    /** Хранит информацию о скорости полета и его слоте */
    private final Map<Integer, Integer> flightSpeedSlot;

    /** Хранит информацию о скорости полета и предмете */
    private final Map<Integer, ItemStack> flightSpeedItem;

    /**
     * Инициализирует объекты меню
     */
    public SpectatorSettingsMenu() {
        setInventory(Bukkit.createInventory(null, 27, "Настройки зрителя"));

        flightSpeedSlot = new HashMap<>();
        flightSpeedSlot.put(1, 11);
        flightSpeedSlot.put(2, 12);
        flightSpeedSlot.put(3, 13);
        flightSpeedSlot.put(4, 14);
        flightSpeedSlot.put(5, 15);

        flightSpeedItem = new HashMap<>();

        // Установить предмет скорости полета, которая выбрана
        ItemStack flightSpeedItem1 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta flightSpeedItem1Meta = flightSpeedItem1.getItemMeta();
        flightSpeedItem1Meta.setDisplayName("§bСкорость полета I");
        List<String> flightSpeedItemSelectedLore = new ArrayList<>();
        flightSpeedItemSelectedLore.add("§7Используйте этот предмет,");
        flightSpeedItemSelectedLore.add("§7чтобы изменить свою скорость полета");
        flightSpeedItemSelectedLore.add("");
        flightSpeedItemSelectedLore.add("§aВыбрано");
        flightSpeedItem1Meta.setLore(flightSpeedItemSelectedLore);
        flightSpeedItem1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        flightSpeedItem1.setItemMeta(flightSpeedItem1Meta);
        flightSpeedItem1.addEnchantment(Enchantment.PROTECTION_FALL, 1);
        flightSpeedItem.put(1, flightSpeedItem1);
        getInventory().setItem(flightSpeedSlot.get(1), flightSpeedItem.get(1));

        // Установить предметы скорости полета, которые не выбраны
        List<String> flightSpeedItemLore = new ArrayList<>();
        flightSpeedItemLore.add("§7Используйте этот предмет,");
        flightSpeedItemLore.add("§7чтобы изменить свою скорость полета");
        flightSpeedItemLore.add("");
        flightSpeedItemLore.add("§eНажмите, чтобы выбрать");

        ItemStack flightSpeedItem2 = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta flightSpeedItem2Meta = flightSpeedItem2.getItemMeta();
        flightSpeedItem2Meta.setDisplayName("§bСкорость полета II");
        flightSpeedItem2Meta.setLore(flightSpeedItemLore);
        flightSpeedItem2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        flightSpeedItem2.setItemMeta(flightSpeedItem2Meta);
        flightSpeedItem.put(2, flightSpeedItem2);
        getInventory().setItem(flightSpeedSlot.get(2), flightSpeedItem.get(2));

        ItemStack flightSpeedItem3 = new ItemStack(Material.IRON_BOOTS);
        ItemMeta flightSpeedItem3Meta = flightSpeedItem3.getItemMeta();
        flightSpeedItem3Meta.setDisplayName("§bСкорость полета III");
        flightSpeedItem3Meta.setLore(flightSpeedItemLore);
        flightSpeedItem3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        flightSpeedItem3.setItemMeta(flightSpeedItem3Meta);
        flightSpeedItem.put(3, flightSpeedItem3);
        getInventory().setItem(flightSpeedSlot.get(3), flightSpeedItem.get(3));

        ItemStack flightSpeedItem4 = new ItemStack(Material.GOLD_BOOTS);
        ItemMeta flightSpeedItem4Meta = flightSpeedItem4.getItemMeta();
        flightSpeedItem4Meta.setDisplayName("§bСкорость полета IV");
        flightSpeedItem4Meta.setLore(flightSpeedItemLore);
        flightSpeedItem4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        flightSpeedItem4.setItemMeta(flightSpeedItem4Meta);
        flightSpeedItem.put(4, flightSpeedItem4);
        getInventory().setItem(flightSpeedSlot.get(4), flightSpeedItem.get(4));

        ItemStack flightSpeedItem5 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta flightSpeedItem5Meta = flightSpeedItem5.getItemMeta();
        flightSpeedItem5Meta.setDisplayName("§bСкорость полета V");
        flightSpeedItem5Meta.setLore(flightSpeedItemLore);
        flightSpeedItem5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        flightSpeedItem5.setItemMeta(flightSpeedItem5Meta);
        flightSpeedItem.put(5, flightSpeedItem5);
        getInventory().setItem(flightSpeedSlot.get(5), flightSpeedItem.get(5));
    }

    /**
     * Устанавливает скорость полета для зрителя по слоту
     * @param player Зритель
     * @param slot Слот
     */
    @Override
    public void clickSlot(Player player, int slot) {
        super.clickSlot(player, slot);

        if(flightSpeedSlot.containsValue(slot)) {
            for(int flightSpeed : flightSpeedSlot.keySet()) {
                int speedSlot = flightSpeedSlot.get(flightSpeed);

                ItemStack item = flightSpeedItem.get(flightSpeed);
                ItemMeta itemMeta = item.getItemMeta();

                if(speedSlot == slot) {
                    List<String> itemLore = new ArrayList<>();
                    itemLore.add("§7Используйте этот предмет,");
                    itemLore.add("§7чтобы изменить свою скорость полета");
                    itemLore.add("");
                    itemLore.add("§aВыбрано");
                    itemMeta.setLore(itemLore);

                    player.setFlySpeed(Float.parseFloat("0." + flightSpeed));
                } else {
                    List<String> itemLore = new ArrayList<>();
                    itemLore.add("§7Используйте этот предмет,");
                    itemLore.add("§7чтобы изменить свою скорость полета");
                    itemLore.add("");
                    itemLore.add("§eНажмите, чтобы выбрать");
                    itemMeta.setLore(itemLore);
                }

                item.setItemMeta(itemMeta);

                // Добавить зачарование о выбранности скорости полета после установки ItemMeta
                // Если добавлять перед установкой ItemMeta, зачарование не добавится
                if(speedSlot == slot) {
                    item.addEnchantment(Enchantment.PROTECTION_FALL, 1);
                } else {
                    item.removeEnchantment(Enchantment.PROTECTION_FALL);
                }

                flightSpeedItem.put(flightSpeed, item);
                getInventory().setItem(flightSpeedSlot.get(flightSpeed), flightSpeedItem.get(flightSpeed));
            }
        }
    }
}