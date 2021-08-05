package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Отвечает за событие нанесения урона по entity
 *
 * @version   05.08.2021
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
            if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING || SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
                // Проверка причины урона на VOID (пустоту)
                if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    // Телепортировать игрока в местоположение спавна
                    player.teleport(Config.lobbyLocation);
                }
                // Отменить урон, так как стадия игры WAITING или STARTING
                event.setCancelled(true);
            }
            // Проверка стадии игры на GAME
            else if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.GAME) {
                // Проверка, есть ли игрок в списке зрителей
                if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                    // Проверка причины урона на VOID (пустоту)
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        // Телепортировать игрока в местоположение спавна зрителей
                        player.teleport(SkyWarsRanked.getGameManager().getPlayerInfo(player).getDeathLocation());
                    }
                    // Отменить урон, так как игрок является зрителем
                    event.setCancelled(true);
                }
            }
            // Проверка стадии игры на ENDING
            else if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.ENDING) {
                // Проверка, есть ли игрок в списке зрителей
                if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                    // Проверка причины урона на VOID (пустоту)
                    if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        // Телепортировать игрока в местоположение спавна зрителей
                        player.teleport(SkyWarsRanked.getGameManager().getPlayerInfo(player).getDeathLocation());
                    }
                }
                // Отменить урон, так как стадия игры ENDING
                event.setCancelled(true);
            }
        }
    }
}