package net.Abdymazhit.SkyWarsRanked.game.menu;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.Mode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Меню телепортации к игрокам
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class TeleportMenu extends Menu {

    /** Хранит информацию о живых игроках с их слотом */
    private final Map<Integer, Player> playersSlot;

    /**
     * Инициализирует объекты меню
     */
    public TeleportMenu() {
        setInventory(Bukkit.createInventory(null, 27, "Телепортация к игрокам"));
        playersSlot = new HashMap<>();
    }

    /**
     * Обновляет меню телепортации к игрокам
     */
    public void update() {
        getInventory().clear();
        playersSlot.clear();

        int slot = 0;
        for(Player player : SkyWarsRanked.getGameManager().getPlayers()) {
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§f" + player.getDisplayName());

            List<String> lore = new ArrayList<>();
            lore.add("§7Набор: §a" + SkyWarsRanked.getGameManager().getPlayerInfo(player).getKit().getName());
            lore.add("");

            if(Config.mode.equals(Mode.RANKED)) {
                lore.add("§7Общий рейтинг: §a" + SkyWarsRanked.getGameManager().getPlayerInfo(player).getOverallRating());
                lore.add("§7Рейтинг за набор: §a" + SkyWarsRanked.getGameManager().getPlayerInfo(player).getOverallRating());
                lore.add("");
            }
            lore.add("§e► Нажмите для телепортации");

            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            getInventory().setItem(slot, item);

            playersSlot.put(slot, player);
            slot++;
        }
    }

    /**
     * Телепортирует зрителя к живому игроку по слоту
     * @param player Зритель
     * @param slot Слот
     */
    @Override
    public void clickSlot(Player player, int slot) {
        super.clickSlot(player, slot);
        Player p = playersSlot.get(slot);
        player.teleport(p.getLocation());
    }
}