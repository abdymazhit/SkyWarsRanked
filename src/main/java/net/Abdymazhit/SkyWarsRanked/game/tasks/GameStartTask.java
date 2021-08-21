package net.Abdymazhit.SkyWarsRanked.game.tasks;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.game.GameEventsManager;
import net.Abdymazhit.SkyWarsRanked.utils.TimerUtils;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Представляет собой игровое событие начала игры
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class GameStartTask extends BukkitRunnable {

    /** Время до начала игры */
    public static int timeLeft = 15;

    /** Менеджер игровых событий */
    private final GameEventsManager gameEventsManager;

    /**
     * Инициализирует объекты
     */
    public GameStartTask(GameEventsManager gameEventsManager) {
        this.gameEventsManager = gameEventsManager;
    }

    /**
     * Запускает обратный отсчет
     */
    @Override
    public void run() {
        // Проверить, не вышли ли игроки с игры
        if (SkyWarsRanked.getGameManager().getPlayers().size() < 2) {
            // Установить игру на WAITING, так как игроки вышли из игры
            gameEventsManager.startWaitingState();
            timeLeft = 15;
            cancel();
        } else {
            // Изменить таймер начала игры в scoreboard'е лобби
            SkyWarsRanked.getGameManager().getLobbyBoard().setStartingStatus("До начала: §a" + TimerUtils.timeToString(timeLeft));

            // Начать стадию игры GAME
            if (timeLeft-- <= 0) {
                gameEventsManager.startGameState();
                timeLeft = 15;
                cancel();
            }
        }
    }
}