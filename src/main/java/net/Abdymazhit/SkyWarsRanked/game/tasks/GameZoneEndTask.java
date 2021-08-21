package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие конца сужения игровой зоны
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class GameZoneEndTask extends BukkitRunnable {

    /** Время до конца сужения игровой зоны */
    private int timeLeft = 30;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public GameZoneEndTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Конец суж. зоны " + TimerUtils.timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft +
                RefillChests2Task.timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о конце сужения зоны
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§fСужение зоны закончилось!", 4, 20, 4);
            }

            // Начать следующее игровое событие
            gameEventsManager.task = new RefillChests2Task(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 30;
            cancel();
        }
    }
}