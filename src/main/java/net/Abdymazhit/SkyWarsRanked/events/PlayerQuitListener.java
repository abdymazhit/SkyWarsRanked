package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Отвечает за событие выхода игрока из сервера
 *
 * @version   03.08.2021
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
                event.setQuitMessage("[" + SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size() + "] " +
                        "§e=> §fИгрок " + player.getDisplayName() + " отключился");

                // Обновить количество игроков в scoreboard'е лобби
                SkyWarsRanked.getLobbyBoard().updatePlayersCount();
            }
            // Проверка, является ли игрок зрителем игры
            else if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                // Удалить игрока из списка зрителей игры
                event.setQuitMessage(null);
                SkyWarsRanked.getGameManager().removeSpectator(player);
            }
        }
        // Проверка стадии игры на GAME
        else if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.GAME)) {
            // Проверка, является ли игрок игроком игры
            if (SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                // Удалить игрока из списка игроков игры
                SkyWarsRanked.getGameManager().removePlayer(player);
                event.setQuitMessage("Игрок " + player.getDisplayName() + " вышел из игры и тем самым был самоубит");

                // Обновить количество живых игроков в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateLivePlayersCount();
            }
            // Проверка, является ли игрок зрителем игры
            else if(SkyWarsRanked.getGameManager().getSpectators().contains(player)) {
                // Удалить игрока из списка зрителей игры
                event.setQuitMessage(null);
                SkyWarsRanked.getGameManager().removeSpectator(player);

                // Обновить количество зрителей в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateSpectatorsCount();
            }
        }
        // Проверка стадии игры на ENDING
        else if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.ENDING)) {
            // Удалить игрока из списка игроков и зрителей игры
            event.setQuitMessage(null);
            SkyWarsRanked.getGameManager().removePlayer(player);
            SkyWarsRanked.getGameManager().removeSpectator(player);
        }
    }
}