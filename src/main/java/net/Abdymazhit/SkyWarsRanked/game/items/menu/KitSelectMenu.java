package net.Abdymazhit.SkyWarsRanked.game.items.menu;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.kits.Kit;
import net.Abdymazhit.SkyWarsRanked.utils.RomanNumber;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Меню выбора набора
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class KitSelectMenu extends Menu {

    /**
     * Инициализирует объекты меню
     */
    public KitSelectMenu(Player player) {
        setInventory(Bukkit.createInventory(null, 45, "Выбор набора"));

        // Получить доступные игроку наборы с их уровнями
        Map<Kit, Integer> kits = SkyWarsRanked.getGameManager().getPlayerInfo(player).getKits();

        // Добавить слоты, которые доступны для наборов
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

        // Установить предметы наборов для выбора
        int index = 0;
        int size = Kit.values().length;
        for(int id = 1; id < size + 1; id++) {
            Kit kit = Kit.getById(id);

            int slot = slots.get(index);
            index++;

            getInventory().setItem(slot, getKitItem(player, kit, kits.getOrDefault(kit, 0)));
        }
    }

    /**
     * Получает предмет набора для выбора
     * @param kit Набор
     * @param level Уровень набора
     * @return Предмет набора для выбора
     */
    public ItemStack getKitItem(Player player, Kit kit, int level) {
        ItemStack itemStack;
        if(level == 0) {
            itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        } else {
            itemStack = new ItemStack(kit.getMaterial());
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<>();
        if(level == 0) {
            itemMeta.setDisplayName("§a" + kit.getName() + " " + RomanNumber.toRoman(1));
            lore.addAll(kit.getLevelDescriptions().get(1));
        } else {
            itemMeta.setDisplayName("§a" + kit.getName() + " " + RomanNumber.toRoman(level));
            lore.addAll(kit.getLevelDescriptions().get(level));
        }
        lore.add("");
        lore.add("§7Редкость: " + kit.getRarity().getName());
        lore.add("");

        if(level == 0) {
            lore.add("§cНе разблокировано");
        } else {
            if(SkyWarsRanked.getGameManager().getPlayerInfo(player).getKit() == kit) {
                lore.add("§aВЫБРАНО");
            } else {
                lore.add("§eНажмите, чтобы выбрать");
            }
        }

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}