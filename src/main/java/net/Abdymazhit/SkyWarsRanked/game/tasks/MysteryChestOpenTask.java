package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие открытия мистического сундука
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class MysteryChestOpenTask extends BukkitRunnable {

    /** Время до открытия мистического сундука */
    public static int timeLeft = 60;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public MysteryChestOpenTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Отк. Мист. сундука " + TimerUtils.timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft +
                MysteryChestCloseTask.timeLeft +
                RefillChestsTask.timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение о открытии мистического сундука
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§dМистический сундук §aоткрыт§d! Поспешите забрать свои законные вещи!");
            }

            // Открыть мистический сундук
            SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().open();

            // Начать следующее игровое событие
            gameEventsManager.task = new MysteryChestCloseTask(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 60;
            cancel();
        }
    }
}