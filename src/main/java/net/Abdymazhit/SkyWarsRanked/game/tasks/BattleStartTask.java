package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие начала битвы
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class BattleStartTask extends BukkitRunnable {

    /** Время до начала битвы */
    public static int timeLeft = 10;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public BattleStartTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Изменить таймер в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Начало битвы " + TimerUtils.timeToString(timeLeft));

        // Отправить сообщение в центр экрана о времени до начале битвы
        if(timeLeft == 5) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§f5", 4, 12, 4);
            }
        } else if(timeLeft == 4) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§f4", 4, 12, 4);
            }
        } else if(timeLeft == 3) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§e3", 4, 12, 4);
            }
        } else if(timeLeft == 2) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§62", 4, 12, 4);
            }
        } else if(timeLeft == 1) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§6§c1", 4, 12, 4);
            }
        }

        // Обновить таймер голограмм открытых сундуков
        gameEventsManager.timeBeforeRefillingChests = timeLeft +
                GameZoneStartTask.timeLeft +
                MysteryChestOpenTask.timeLeft +
                MysteryChestCloseTask.timeLeft +
                RefillChestsTask.timeLeft;
        SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(gameEventsManager.timeBeforeRefillingChests);

        // Проверка, началось ли событие
        if (timeLeft-- <= 0) {
            // Отправить сообщение в центр экрана о начале битвы
            for(Player player : Bukkit.getOnlinePlayers()) {
                NMS.sendTitle(player, "§6ИГРА НАЧАЛАСЬ!", 4, 12, 4);
            }

            // Разрешить PvP между игроками
            SkyWarsRanked.getGameManager().setEnabledPvP(true);

            // Начать следующее игровое событие
            gameEventsManager.task = new GameZoneStartTask(gameEventsManager);
            gameEventsManager.task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            // Отменить текущее событие
            timeLeft = 10;
            cancel();
        }
    }
}