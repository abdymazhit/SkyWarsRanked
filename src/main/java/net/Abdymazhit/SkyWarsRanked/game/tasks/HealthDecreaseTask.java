package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие снижения здоровья у игроков
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class HealthDecreaseTask extends BukkitRunnable {

    /** Время до начала снижения здоровья у игроков */
    private int timeLeft = 60;

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Сниж. здор. игроков " + TimerUtils.timeToString(timeLeft));

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщения в центр экрана о начале снижения здоровья игроков
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§cСнижение здоровья игроков!", 4, 20, 4);
            }

            // Начать снижение здоровья игроков
            // Каждую секунду здоровье будет уменьшаться на 2 хп
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player player : SkyWarsRanked.getGameManager().getPlayers()) {
                        player.damage(2);
                    }

                    if(SkyWarsRanked.getGameManager().getPlayers().size() == 1) {
                        timeLeft = 60;
                        cancel();
                    }
                }
            }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 60;
            cancel();
        }
    }
}