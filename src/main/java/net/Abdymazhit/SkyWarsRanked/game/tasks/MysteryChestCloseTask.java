package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие закрытия мистического сундука
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class MysteryChestCloseTask extends BukkitRunnable {

    /** Время до закрытия мистического сундука */
    public static int timeLeft = 30;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public MysteryChestCloseTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Закр. Мист. сундука " + TimerUtils.timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft +
                RefillChestsTask.timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение о закрытии мистического сундука
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§dМистический сундук §cзакрылся§d!");
            }

            // Закрыть мистический сундук
            SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().close();

            // Начать следующее игровое событие
            gameEventsManager.task = new RefillChestsTask(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 30;
            cancel();
        }
    }
}