package net.Abdymazhit.SkyWarsRanked.game.events.cancelled;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.StructureGrowEvent;

/**
 * Отменяет события связанные с миром
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class WorldEventsListener implements Listener {

    /**
     * Событие создания портала
     */
    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие роста блоков (саженец, дерево и т.д.)
     */
    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        event.setCancelled(true);
    }
}