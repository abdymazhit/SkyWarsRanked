package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Отвечает за событие взаимодействия игрока с объектом
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerInteractListener implements Listener {

    /**
     * Событие взаимодействия игрока с объектом
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Проверка, является ли игрок зрителем
        if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
            // Проверка клика на правый
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }

            // Проверка существует ли предмет
            if(event.getItem() == null) {
                return;
            }

            // Использовать предмет игроку
            SkyWarsRanked.getGameItems().useItem(player, event.getItem());
        }
        // Проверка стадии игры на WAITING или STARTING
        else if(SkyWarsRanked.getGameManager().getGameStage() == GameStage.WAITING || SkyWarsRanked.getGameManager().getGameStage() == GameStage.STARTING) {
            // Отменить событие, чтобы игрок не мог взаимодействовать с блоками в острове лобби
            event.setCancelled(true);

            // Проверка клика на правый
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }

            // Проверка существует ли предмет
            if(event.getItem() == null) {
                return;
            }

            // Использовать предмет игроку
            SkyWarsRanked.getGameItems().useItem(player, event.getItem());
        }
    }
}