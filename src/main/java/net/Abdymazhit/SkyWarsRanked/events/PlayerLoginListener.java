package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Отвечает за событие попытки входа игрока в сервер
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerLoginListener implements Listener {

    /**
     * Событие попытки входа игрока в сервер
     */
    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        // Получить информацию о игроке от API VimeWorld.ru
        PlayerInfo playerInfo = SkyWarsRanked.getApi().getPlayerInfo(player);
        if(playerInfo != null) {
            SkyWarsRanked.getGameManager().addPlayerInfo(player, playerInfo);
        } else {
            player.kickPlayer("Ошибка получения информации от API VimeWorld.ru");
        }
    }
}