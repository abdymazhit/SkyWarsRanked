package net.Abdymazhit.SkyWarsRanked.game.events.cancelled;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Отменяет события связанные с entity
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityEventsListener implements Listener {

    /**
     * Событие рождения entity
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        // Проверка причины спавна на CUSTOM (через плагин) или SPAWNER_EGG (через яйцо призыва)
        if(!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && !event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие возгорания entity
     */
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения уровня голода
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}