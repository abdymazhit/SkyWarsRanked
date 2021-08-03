package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Менеджер игровых событий, отвечает за изменение игровых событий
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class GameEventsManager {

    /**
     * Начать игровое событие начала битвы
     */
    public void startBattleStartEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 10; // Время до начала битвы

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Начало битвы " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startMysteryChestOpenEvent();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие открытия мистического сундука
     */
    private void startMysteryChestOpenEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 140; // Время до открытия мистического сундука

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Отк. Мист. сундука " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startMysteryChestCloseEvent();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие закрытия мистического сундука
     */
    private void startMysteryChestCloseEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 30; // Время до закрытия мистического сундука

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Закр. Мист. сундука " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startRefillChestsEvent();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие перезаполнения сундуков
     */
    private void startRefillChestsEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 120; // Время до перезаполнения сундуков

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startMysteryChestOpenEventV2();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие открытия мистического сундука
     */
    private void startMysteryChestOpenEventV2() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 150; // Время до открытия мистического сундука

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Отк. Мист. сундука " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startMysteryChestCloseEventV2();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие закрытия мистического сундука
     */
    private void startMysteryChestCloseEventV2() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 30; // Время до закрытия мистического сундука

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Закр. Мист. сундука " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startRefillChestsEventV2();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие перезаполнения сундуков
     */
    private void startRefillChestsEventV2() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 120; // Время до перезаполнения сундуков

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startDeathmatchEvent();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие детматча
     */
    private void startDeathmatchEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 150; // Время до детматча

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Детматч " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            startMegaDeathmatchEvent();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать игровое событие мега детматча
     */
    private void startMegaDeathmatchEvent() {
        // Начать обратный отсчет
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 150; // Время до мега детматча

                    @Override
                    public void run() {
                        // Изменить таймер в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Мега Детматч " + timeToString(time));

                        // Начать следующее игровое событие
                        if (time-- <= 0) {
                            // Начать стадию конца игры, так как не удалось выявить победителя
                            SkyWarsRanked.getGameStageManager().startEndingStage();
                            cancel();
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Конвертировать время типа Integer в тип String
     * @param time Время типа Integer
     * @return Время в типе String
     */
    private String timeToString(int time) {
        int min = time / 60 % 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);
    }
}