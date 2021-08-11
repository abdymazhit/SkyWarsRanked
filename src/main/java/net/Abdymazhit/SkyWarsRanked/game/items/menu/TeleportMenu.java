package net.Abdymazhit.SkyWarsRanked.game.items.menu;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Меню телепортации к игрокам
 *
 * @version   11.08.2021
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
            itemMeta.setLore(Collections.singletonList("§5§oНажмите для телепортации"));
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