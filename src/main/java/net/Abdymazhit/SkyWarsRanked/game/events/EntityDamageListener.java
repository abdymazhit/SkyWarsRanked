package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Отвечает за событие нанесения урона по entity
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityDamageListener implements Listener {

    /**
     * Событие нанесения урона по entity
     */
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // Проверка стадии игры на WAITING или STARTING
            if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
                // Проверка причины урона на VOID (пустоту)
                if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    // Телепортировать игрока в местоположение спавна
                    player.teleport(Config.lobbyLocation);
                }
                // Отменить урон, так как стадия игры WAITING или STARTING
                event.setCancelled(true);
            }
            // Проверка стадии игры на GAME
            else if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.GAME)) {
                // Проверка, есть ли игрок в списке зрителей
                if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                    // Проверка причины урона на VOID (пустоту)
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        // Телепортировать игрока в местоположение лобби
                        player.teleport(Config.lobbyLocation);
                    }
                    // Отменить урон, так как игрок является зрителем
                    event.setCancelled(true);
                }
            }
            // Проверка стадии игры на ENDING
            else if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.ENDING)) {
                // Проверка, есть ли игрок в списке зрителей
                if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                    // Проверка причины урона на VOID (пустоту)
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        // Телепортировать игрока в местоположение лобби
                        player.teleport(Config.lobbyLocation);
                    }
                }
                // Отменить урон, так как стадия игры ENDING
                event.setCancelled(true);
            }
        }
    }
}