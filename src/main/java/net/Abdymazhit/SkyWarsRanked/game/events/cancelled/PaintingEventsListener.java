package net.Abdymazhit.SkyWarsRanked.game.events.cancelled;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;

/**
 * Отменяет события связанные с картинами
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class PaintingEventsListener implements Listener {

    /**
     * Событие ломания картины от entity
     */
    @EventHandler
    public void onPaintingBreakByEntity(PaintingBreakByEntityEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие ломания картины
     */
    @EventHandler
    public void onPaintingBreakEvent(PaintingBreakEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие поставки картины
     */
    @EventHandler
    public void onPaintingPlace(PaintingPlaceEvent event) {
        event.setCancelled(true);
    }
}