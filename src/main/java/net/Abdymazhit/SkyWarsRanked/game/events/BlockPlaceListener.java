package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Отвечает за событие поставки блока игроком
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class BlockPlaceListener implements Listener {

    /**
     * Событие поставки блока игроком
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }

        // Увеличить количество поставленных блоков игрока за матч
        if(!event.isCancelled()) {
            SkyWarsRanked.getGameManager().getPlayerInfo(event.getPlayer()).addBlocksPlaced();
        }
    }
}