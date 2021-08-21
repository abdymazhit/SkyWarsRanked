package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие конца игры
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class GameEndTask extends BukkitRunnable {

    /** Время до конца игры */
    public static int timeLeft = 15;

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Обновить таймер конца игры в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Конец игры " + TimerUtils.timeToString(timeLeft));

        // Завершить игру
        if (timeLeft-- <= 0) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer("Игра завершена");
            }

            Bukkit.getServer().unloadWorld(Config.world, true);
            Bukkit.shutdown();

            timeLeft = 15;
            cancel();
        }
    }
}