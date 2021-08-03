package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Отвечает за событие входа игрока в сервер
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerJoinListener implements Listener {

    /**
     * Событие входа игрока в сервер
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Проверка стадии игры на WAITING и STARTING
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            // Установить для игрока scoreboard лобби
            SkyWarsRanked.getLobbyBoard().setScoreboard(player);
            SkyWarsRanked.getLobbyBoard().setWaitingStatus(player);

            // Проверка, не набрано ли максимальное количество игроков
            if(SkyWarsRanked.getGameManager().getPlayers().size() < Config.islands.size()) {
                // Добавить игрока в игру
                SkyWarsRanked.getGameManager().addPlayer(player);
                event.setJoinMessage("[" + SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size() + "] " +
                        "§e=> §fИгрок " + player.getDisplayName() + " подключился");

                // Обновить количество игроков в scoreboard'е лобби
                SkyWarsRanked.getLobbyBoard().updatePlayersCount();
            } else {
                // Добавить зрителя в игру, так как набрано максимальное количество игроков
                event.setJoinMessage(null);
                SkyWarsRanked.getGameManager().addSpectator(player);
            }
        } else {
            // Добавить зрителя в игру, так как стадия игры не является WAITING или STARTING
            event.setJoinMessage(null);
            SkyWarsRanked.getGameManager().getSpectators().add(player);
        }
    }
}
