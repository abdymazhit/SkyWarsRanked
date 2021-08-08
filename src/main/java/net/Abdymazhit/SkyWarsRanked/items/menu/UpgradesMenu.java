package net.Abdymazhit.SkyWarsRanked.items.menu;

import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Меню прокачек игрока
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public class UpgradesMenu extends Menu {

    /**
     * Инициализирует объекты меню
     */
    public UpgradesMenu(Map<Upgrade, Integer> upgrades) {
        setInventory(Bukkit.createInventory(null, 45, "Ваши прокачки"));

        // Добавить слоты, которые доступны для прокачек
        List<Integer> slots = new ArrayList<>();
        for(int slot = 10; slot < 17; slot++) {
            slots.add(slot);
        }

        for(int slot = 19; slot < 26; slot++) {
            slots.add(slot);
        }

        for(int slot = 28; slot < 35; slot++) {
            slots.add(slot);
        }

        // Установить предметы прокачек
        int index = 0;
        int size = Upgrade.values().length;
        for(int id = 1; id < size + 1; id++) {
            Upgrade upgrade = Upgrade.getById(id);

            int slot = slots.get(index);
            index++;

            getInventory().setItem(slot, getUpgradeItem(upgrade, upgrades.getOrDefault(upgrade, 0)));
        }
    }

    /**
     * Получает предмет прокачки
     * @param upgrade Прокачка
     * @param level Уровень прокачки
     * @return Предмет прокачки
     */
    public ItemStack getUpgradeItem(Upgrade upgrade, int level) {
        ItemStack itemStack = new ItemStack(upgrade.getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<>();
        for(String description : upgrade.getDescription()) {
            String desc = description.replace("<upgrade>", upgrade.getLevelUpgrade().getOrDefault(level, 0).toString());
            lore.add(desc);
        }

        lore.add("");
        lore.add("§7Редкость: " + upgrade.getRarity().getName());
        lore.add("");

        int maxLevel = Collections.max(upgrade.getLevelUpgrade().keySet());
        if(level == maxLevel) {
            lore.add("§c§lМАКСИМАЛЬНЫЙ УРОВЕНЬ");
        } else {
            lore.add("§eМожно улучшить в лобби!");
        }

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(upgrade.getName());
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}