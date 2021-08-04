package net.Abdymazhit.SkyWarsRanked.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Отвечает за событие смерти игрока
 *
 * @version   04.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerDeathListener implements Listener {

    /**
     * Событие смерти игрока
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        player.setHealth(20);

        // Телепортировать игрока в местоположение спавна зрителей
        player.teleport(Config.spectatorLocation);

        // Удалить игрока из списка живых игроков и обновить количество живых игроков в scoreboard'е игры
        SkyWarsRanked.getGameManager().removePlayer(player);
        SkyWarsRanked.getGameBoard().updateLivePlayersCount();

        // Добавить игрока в список зрителей и обновить количество зрителей в scoreboard'е игры
        SkyWarsRanked.getGameManager().addSpectator(player);
        SkyWarsRanked.getGameBoard().updateSpectatorsCount();

        // Получить убийцу игрока (последнего нанесшего урон)
        Player killer = SkyWarsRanked.getGameManager().getPlayerInfo(player).getLastDamager();

        // Проверить, существует ли последний нанесший урон
        if(killer != null) {
            // Отправить сообщения о убийстве
            event.setDeathMessage("Игрок " + player.getName() + " убит игроком " + killer.getName());
            player.sendMessage("Вас убил игрок §c" + killer.getName() + " §fи у него осталось §c" + (killer.getHealth() / 2) + "❤");
        } else {
            // Отправить сообщения о убийстве
            event.setDeathMessage("Игрок " + player.getName() + " самоубился");
            player.sendMessage("Вы самоубились");
        }

        // Проверить, есть ли победитель игры
        if(SkyWarsRanked.getGameManager().getPlayers().size() == 1) {
            Player winner = SkyWarsRanked.getGameManager().getPlayers().get(0);
            
            // Отправить сообщение о победителе
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§7####################################");
                p.sendMessage("§7# §fПобедитель:");
                p.sendMessage("§7#     §f" + winner.getName());
                p.sendMessage("§7####################################");
            }
            
            // Начать стадию конца игры
            SkyWarsRanked.getGameStageManager().startEndingStage();
        }
    }
}