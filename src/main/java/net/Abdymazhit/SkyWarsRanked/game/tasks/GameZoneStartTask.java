package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие начала сужения зоны
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class GameZoneStartTask extends BukkitRunnable {

    /** Время до начала сужения зоны */
    public static int timeLeft = 50;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public GameZoneStartTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Начало суж. зоны " + TimerUtils.timeToString(timeLeft));

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft +
                MysteryChestOpenTask.timeLeft +
                MysteryChestCloseTask.timeLeft +
                RefillChestsTask.timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о начале сужения зоны
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§fСужение зоны началось!", 4, 20, 4);
            }

            // Начать сужение зоны
            WorldBorder worldBorder = Config.world.getWorldBorder();
            worldBorder.setSize(40, 180);

            // Начать следующее игровое событие
            gameEventsManager.task = new MysteryChestOpenTask(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 50;
            cancel();
        }
    }
}