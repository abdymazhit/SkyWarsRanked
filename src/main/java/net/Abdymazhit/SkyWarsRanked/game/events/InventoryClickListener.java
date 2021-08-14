package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EnchantingInventory;

/**
 * Отвечает за событие клика по слоту инвентаря
 *
 * @version   14.08.2021
 * @author    Islam Abdymazhit
 */
public class InventoryClickListener implements Listener {

    /**
     * Событие клика по слоту инвентаря
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Проверка, существует ли предмет и является ли кликнувший игроком
        if(event.getCurrentItem() != null && event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            // Проверка стадии игры на WAITING или STARTING
            if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
                // Отменить клик, так как стадия игры WAITING или STARTING
                event.setCancelled(true);

                // Проверка существует ли предмет
                if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                    return;
                }

                // Отправляет событие клика по инвентарю
                SkyWarsRanked.getGameManager().getGameItems().clickInventory(player, event.getInventory(), event.getSlot());
            }

            // Проверка, пытается ли игрок забрать лазурит с стола зачарований
            if (event.getClickedInventory() instanceof EnchantingInventory && event.getSlot() == 1) {
                // Отменить клик, так как игрок пытается забрать себе лазурит
                event.setCancelled(true);
            }

            // Проверить, является ли игрок зрителем, если да - отменяем клик по слоту
            if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                // Отменить клик, так как игрок является зрителем
                event.setCancelled(true);

                // Проверка существует ли предмет
                if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                    return;
                }

                // Отправляет событие клика по инвентарю
                SkyWarsRanked.getGameManager().getGameItems().clickInventory(player, event.getInventory(), event.getSlot());
            }
        }
    }
}