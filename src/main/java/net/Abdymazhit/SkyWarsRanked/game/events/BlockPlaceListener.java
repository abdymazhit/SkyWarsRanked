package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Отвечает за событие поставки блока игроком
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class BlockPlaceListener implements Listener {

    /**
     * Событие поставки блока игроком
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
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