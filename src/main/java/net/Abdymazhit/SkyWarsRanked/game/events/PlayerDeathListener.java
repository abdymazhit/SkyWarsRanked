package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Отвечает за событие смерти игрока
 *
 * @version   11.08.2021
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
        event.setDeathMessage(null);

        // Выполнить действия убийства игрока
        SkyWarsRanked.getGameManager().performKillEvent(player);
    }
}