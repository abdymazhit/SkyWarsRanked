package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Отвечает за событие выхода игрока из сервера
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerQuitListener implements Listener {

    /**
     * Событие выхода игрока из сервера
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Проверка стадии игры на WAITING или STARTING
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            // Проверка, является ли игрок игроком игры
            if(SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                // Убрать игрока из острова
                for(Island island : Config.islands) {
                    if(island.getPlayer() != null) {
                        if(island.getPlayer().equals(player)) {
                            island.setPlayer(null);
                        }
                    }
                }

                // Удалить игрока из списка игроков игры
                SkyWarsRanked.getGameManager().removePlayer(player);

                // Обновить количество игроков в scoreboard'е лобби
                SkyWarsRanked.getGameManager().getLobbyBoard().updatePlayersCount();

                // Отправить сообщение о выходе игрока
                event.setQuitMessage("[" + SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size() + "] " +
                        "§e=> §fИгрок " + player.getDisplayName() + " отключился");
            }
            // Проверка, является ли игрок зрителем игры
            else if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                // Удалить игрока из списка зрителей игры
                SkyWarsRanked.getGameManager().removeSpectator(player);

                // Не отображать сообщение о выходе, так как игрок является зрителем
                event.setQuitMessage(null);
            }
        }
        // Проверка стадии игры на GAME
        else if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.GAME)) {
            // Проверка, является ли игрок игроком игры
            if (SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                // Выполнить действия убийства игрока
                SkyWarsRanked.getGameManager().performKillEvent(player);

                // Не отображать сообщение о выходе
                event.setQuitMessage(null);
            }
            // Проверка, является ли игрок зрителем игры
            else if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                // Удалить игрока из списка зрителей игры
                SkyWarsRanked.getGameManager().removeSpectator(player);

                // Обновить количество зрителей в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateSpectatorsCount();

                // Не отображать сообщение о выходе, так как игрок является зрителем
                event.setQuitMessage(null);
            }
        }
        // Проверка стадии игры на ENDING
        else if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.ENDING)) {
            // Удалить игрока из списка игроков и зрителей игры
            SkyWarsRanked.getGameManager().removePlayer(player);
            SkyWarsRanked.getGameManager().removeSpectator(player);

            // Не отображать сообщение о выходе, так как стадия игры ENDING
            event.setQuitMessage(null);
        }

        // Удаляет информацию о игроке, полученную от API VimeWorld.ru
        SkyWarsRanked.getGameManager().removePlayerInfo(player);
    }
}