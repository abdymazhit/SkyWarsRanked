package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Отвечает за событие нанесения урона по entity от другого entity
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityDamageByEntityListener implements Listener {

    /**
     * Событие нанесения урона по entity от другого entity
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.GAME)) {
            if(event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if(event.getDamager() instanceof Player) {
                    // Отменить событие, если событие начала битвы не началось
                    if(!SkyWarsRanked.getGameSettingsManager().isEnabledPvP()) {
                        event.setCancelled(true);
                    }
                    // Установить игроку последнего нанесшего урон по нему игрока
                    else {
                        Player damager = (Player) event.getDamager();
                        SkyWarsRanked.getGameManager().getPlayerInfo(player).setLastDamager(damager);
                    }
                }
            }
        }
    }
}