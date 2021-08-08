package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Отвечает за событие стрельбы из лука entity
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public class EntityShootBowListener implements Listener {

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

            if(!event.isCancelled()) {
                // Увеличить количество выпущенных стрел игрока за матч
                SkyWarsRanked.getGameManager().getPlayerInfo(player).addArrowsFired();

                // Выполнить действия прокачки Пылающие стрелы
                if(Upgrade.performUpgradeAction(player, Upgrade.BLAZING_ARROWS)) {
                    event.getProjectile().setFireTicks(Integer.MAX_VALUE);
                }
            }
        }
    }
}