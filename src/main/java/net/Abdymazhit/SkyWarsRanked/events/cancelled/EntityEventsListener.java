package net.Abdymazhit.SkyWarsRanked.events.cancelled;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

/**
 * Отменяет события связанные с entity
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityEventsListener implements Listener {

    /**
     * Событие рождения entity
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        // Проверка причины спавна на CUSTOM (через плагин) или SPAWNER_EGG (через яйцо призыва)
        if(event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) || event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие возгорания entity
     */
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие стрельбы из лука entity
     */
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Событие изменения уровня голода
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Событие смерти игрока
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getEntity().setHealth(20);
    }
}