package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Отвечает за событие выхода игрока из сервера
 *
 * @version   21.08.2021
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
        if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.WAITING) || SkyWarsRanked.getGameManager().getGameState().equals(GameState.STARTING)) {
            // Проверка, является ли игрок игроком игры
            if(SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                // Убрать игрока из острова
                for(Island island : Config.islands) {
                    if(island.getPlayers().contains(player)) {
                        island.removePlayer(player);
                    }
                }

                // Удалить игрока из списка игроков игры
                SkyWarsRanked.getGameManager().removePlayer(player);

                // Обновить количество игроков в scoreboard'е лобби
                SkyWarsRanked.getGameManager().getLobbyBoard().updatePlayersCount();

                // Обновляем меню выбора острова
                SkyWarsRanked.getGameManager().getGameItemsManager().getIslandSelectMenu().update();

                // Отправить сообщение о выходе игрока
                event.setQuitMessage("[" + SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size() * Config.islandPlayers + "] " +
                        "§e=> §fИгрок " + player.getDisplayName() + " §fотключился");
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
        else if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.GAME)) {
            // Проверка, является ли игрок игроком игры
            if (SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                // Выполнить действия убийства игрока
                SkyWarsRanked.getGameManager().performKillEvent(player);

                // Удалить игрока из списка зрителей игры
                SkyWarsRanked.getGameManager().removeSpectator(player);

                // Обновить количество зрителей в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateSpectatorsCount();

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
        else if(SkyWarsRanked.getGameManager().getGameState().equals(GameState.ENDING)) {
            // Удалить игрока из списка игроков и зрителей игры
            SkyWarsRanked.getGameManager().removePlayer(player);
            SkyWarsRanked.getGameManager().removeSpectator(player);

            // Обновить количество зрителей в scoreboard'е игры
            SkyWarsRanked.getGameManager().getGameBoard().updateSpectatorsCount();

            // Не отображать сообщение о выходе, так как стадия игры ENDING
            event.setQuitMessage(null);
        }

        // Удаляет информацию о игроке, полученную от API VimeWorld.ru
        SkyWarsRanked.getGameManager().removePlayerInfo(player);
    }
}