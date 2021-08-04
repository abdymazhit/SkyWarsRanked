package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Отвечает за событие смерти игрока
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerDeathListener implements Listener {

    /**
     * Событие смерти игрока
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        player.setHealth(20);
        player.teleport(Config.spectatorLocation);
    }
}