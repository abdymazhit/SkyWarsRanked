package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import net.Abdymazhit.SkyWarsRanked.enums.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Отвечает за событие попытки входа игрока в сервер
 *
 * @version   21.08.2021
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
            player.setDisplayName(getPrefixedName(playerInfo, player.getName()));
            player.setPlayerListName(getColoredName(playerInfo, player.getName()));

            SkyWarsRanked.getGameManager().addPlayerInfo(player, playerInfo);
        } else {
            player.kickPlayer("Ошибка получения информации от API VimeWorld.ru");
        }
    }

    /**
     * Получает имя игрока с префиксом
     * @param playerInfo Информация о игроке
     * @param playerName Имя игрока
     * @return Имя игрока с префиксом
     */
    private String getPrefixedName(PlayerInfo playerInfo, String playerName) {
        Rank rank = playerInfo.getPlayerVimeInfo().getRank();
        String prefix = rank.getPrefix();

        if (!prefix.isEmpty()) {
            return rank.getColor() + "[" + prefix + ChatColor.RESET + rank.getColor() + "] " + playerName + ChatColor.RESET;
        }
        return rank.getColor() + playerName + ChatColor.RESET;
    }

    /**
     * Получает имя игрока с цветом
     * @param playerInfo Информация о игрке
     * @param playerName Имя игрока
     * @return Имя игрока с цветом
     */
    private String getColoredName(PlayerInfo playerInfo, String playerName) {
        Rank rank = playerInfo.getPlayerVimeInfo().getRank();
        return rank.getColor() + playerName;
    }
}