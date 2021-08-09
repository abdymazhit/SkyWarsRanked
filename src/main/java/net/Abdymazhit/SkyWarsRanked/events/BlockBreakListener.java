package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Отвечает за событие ломания блока игроком
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class BlockBreakListener implements Listener {

    /**
     * Событие ломания блока игроком
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }

        // Увеличить количество сломанных блоков игрока за матч
        if(!event.isCancelled()) {
            SkyWarsRanked.getGameManager().getPlayerInfo(event.getPlayer()).addBlocksBroken();
        }
    }
}