package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Отвечает за событие входа игрока в сервер
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerJoinListener implements Listener {

    /**
     * Событие входа игрока в сервер
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Проверка стадии игры на WAITING или STARTING
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            // Установить игроку scoreboard лобби
            SkyWarsRanked.getGameManager().getLobbyBoard().setScoreboard(player);

            // Установить игроку статус scoreboard'а лобби на WAITING
            SkyWarsRanked.getGameManager().getLobbyBoard().setWaitingStatus(player);

            // Проверка, не набрано ли максимальное количество игроков
            if(SkyWarsRanked.getGameManager().getPlayers().size() < Config.islands.size()) {
                // Добавить игрока в список игроков игры
                SkyWarsRanked.getGameManager().addPlayer(player);
                event.setJoinMessage("[" + SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size() + "] " +
                        "§e=> §fИгрок " + player.getDisplayName() + " подключился");

                // Обновить количество игроков в scoreboard'е лобби
                SkyWarsRanked.getGameManager().getLobbyBoard().updatePlayersCount();
            } else {
                // Добавить игрока в список зрителей игры, так как набрано максимальное количество игроков
                event.setJoinMessage(null);
                SkyWarsRanked.getGameManager().addSpectator(player);
            }
        } else {
            // Установить игроку scoreboard игры
            SkyWarsRanked.getGameManager().getGameBoard().setScoreboard(player);

            // Добавить игрока в список зрителей игры, так как стадия игры не является WAITING или STARTING
            event.setJoinMessage(null);
            SkyWarsRanked.getGameManager().addSpectator(player);

            // Обновить количество зрителей в scoreboard'е игры
            SkyWarsRanked.getGameManager().getGameBoard().updateSpectatorsCount();
        }
    }
}