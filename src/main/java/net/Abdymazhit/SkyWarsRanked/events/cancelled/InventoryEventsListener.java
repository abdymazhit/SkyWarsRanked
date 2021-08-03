package net.Abdymazhit.SkyWarsRanked.events.cancelled;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

/**
 * Отменяет события связанные с инвентарем
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class InventoryEventsListener implements Listener {

    /**
     * Событие плавления предмета в печке
     */
    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING ||
                SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие извлечения предмета из печки
     */
    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING ||
                SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            event.setExpToDrop(0);
        }
    }

    /**
     * Событие завершения плавления предмета в печке
     */
    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING ||
                SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие перетаскивания предмета в инвентаре
     */
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING ||
                SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            event.setCancelled(true);
        }

        if(event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Событие поднятия предмета инвентарём (хоппер)
     */
    @EventHandler
    public void onInventoryPickupItem(InventoryPickupItemEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING ||
                SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            event.setCancelled(true);
        }
    }
}