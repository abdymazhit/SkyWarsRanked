package net.Abdymazhit.SkyWarsRanked.game.events;

import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

/**
 * Отвечает за событие открытия инвентаря
 *
 * @version   14.08.2021
 * @author    Islam Abdymazhit
 */
public class InventoryOpenListener implements Listener {

    /**
     * Событие открытия инвентаря
     */
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        // Проверка, является ли инвентарь столом зачарований
        if (event.getInventory() instanceof EnchantingInventory) {
            // Добавить в стол зачарований лазурит
            Dye dye = new Dye();
            dye.setColor(DyeColor.BLUE);
            ItemStack itemStack = dye.toItemStack(3);
            event.getInventory().setItem(1, itemStack);
        }
    }
}
