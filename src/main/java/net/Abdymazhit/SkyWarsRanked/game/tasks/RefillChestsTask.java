package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие перезаполнения сундуков
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class RefillChestsTask extends BukkitRunnable {

    /** Время до перезаполнения сундуков */
    public static int timeLeft = 60;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public RefillChestsTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Перезап. сундуков " + TimerUtils.timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Удалить голограммы сундуков
            SkyWarsRanked.getGameManager().getChestManager().removeOpenedChestsHolograms();
            SkyWarsRanked.getGameManager().getChestManager().removeEmptyChestsHolograms();

            // Перезаполнить сундуки лутом
            SkyWarsRanked.getGameManager().getChestManager().refillIslandChests();
            SkyWarsRanked.getGameManager().getChestManager().refillBasicChests();
            SkyWarsRanked.getGameManager().getChestManager().refillMiddleChests();

            // Отправить звук о перезаполнении сундуков
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1, 1);
            }

            // Начать следующее игровое событие
            gameEventsManager.task = new GameZoneEndTask(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 60;
            cancel();
        }
    }
}