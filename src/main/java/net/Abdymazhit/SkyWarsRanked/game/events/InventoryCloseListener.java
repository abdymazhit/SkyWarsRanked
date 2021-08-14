package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Отвечает за событие закрытия инвентаря
 *
 * @version   14.08.2021
 * @author    Islam Abdymazhit
 */
public class InventoryCloseListener implements Listener {

    /**
     * Событие закрытия инвентаря
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Проверка, является ли инвентарь сундуком
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
            Player player = (Player) event.getPlayer();

            Chest chest = SkyWarsRanked.getGameManager().getChestManager().getCurrentOpenedPlayerChest(player);
            if(chest != null) {
                // Удалить текущий открытый сундук игрока
                SkyWarsRanked.getGameManager().getChestManager().setCurrentOpenedPlayerChest(player, null);

                // Добавить голограмму пустого сундука, если инвентарь является пустым
                boolean isEmpty = true;
                for(ItemStack item : event.getInventory().getContents()) {
                    if (item != null && item.getType() != Material.AIR) {
                        isEmpty = false;
                    }
                }

                if(isEmpty) {
                    SkyWarsRanked.getGameManager().getChestManager().addEmptyChestHologram(chest);
                } else {
                    SkyWarsRanked.getGameManager().getChestManager().removeEmptyChestHologram(chest);
                }
            }
        }
        // Проверка, является ли инвентарь столом зачарований
        else if (event.getInventory() instanceof EnchantingInventory) {
            event.getInventory().setItem(1, null);
        }
    }
}
