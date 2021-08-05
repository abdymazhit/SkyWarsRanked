package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Менеджер игровых событий, отвечает за изменение игровых событий
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class GameEventsManager {

    /** Таймер обратного отсчета */
    public BukkitTask task;

    /**
     * Начать игровое событие начала битвы
     */
    public void startBattleStartEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 10; // Время до начала битвы

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Начало битвы " + timeToString(time));

                if (time-- <= 0) {
                    // Разрешить PvP между игроками
                    SkyWarsRanked.getGameSettingsManager().setEnabledPvP(true);

                    // Начать следующее игровое событие
                    startNarrowingGameZoneEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /** Начать игровое событие начала сужения зоны */
    private void startNarrowingGameZoneEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 50; // Время до начала сужения зоны

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Начало суж. зоны " + timeToString(time));

                if (time-- <= 0) {
                    // Начать сужение зоны
                    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
                    worldBorder.setSize(20, 180);

                    // Начать следующее игровое событие
                    startMysteryChestOpenEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие открытия мистического сундука
     */
    private void startMysteryChestOpenEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 60; // Время до открытия мистического сундука

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Отк. Мист. сундука " + timeToString(time));

                if (time-- <= 0) {
                    // Открыть мистический сундук
                    SkyWarsRanked.getGameSettingsManager().setMysteryChestOpened(true);

                    // Начать следующее игровое событие
                    startMysteryChestCloseEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие закрытия мистического сундука
     */
    private void startMysteryChestCloseEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 30; // Время до закрытия мистического сундука

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Закр. Мист. сундука " + timeToString(time));

                if (time-- <= 0) {
                    // Закрыть мистический сундук
                    SkyWarsRanked.getGameSettingsManager().setMysteryChestOpened(false);

                    // Начать следующее игровое событие
                    startRefillChestsEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие перезаполнения сундуков
     */
    private void startRefillChestsEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 60; // Время до перезаполнения сундуков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));

                if (time-- <= 0) {
                    // Начать следующее игровое событие
                    startEndNarrowingGameZoneEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /** Начать игровое событие конца сужения зоны */
    private void startEndNarrowingGameZoneEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 30; // Время до конца сужения зоны

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Конец суж. зоны " + timeToString(time));

                if (time-- <= 0) {
                    // Начать следующее игровое событие
                    startRefillChestsEventV2();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие перезаполнения сундуков
     */
    private void startRefillChestsEventV2() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 60; // Время до перезаполнения сундуков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));

                if (time-- <= 0) {
                    // Начать следующее игровое событие
                    startDeathmatchEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие детматча
     */
    private void startDeathmatchEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 60; // Время до детматча

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Детматч " + timeToString(time));

                if (time-- <= 0) {
                    // Телепортировать игроков в местоположение детматча
                    for(int id = 0; id < SkyWarsRanked.getGameManager().getPlayers().size(); id++) {
                        Player player = SkyWarsRanked.getGameManager().getPlayers().get(id);
                        player.teleport(Config.deathmatchSpawns.get(id));
                    }

                    // Начать следующее игровое событие
                    startPlayersDecreasingHealthEvent();
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начать игровое событие снижения здоровья игроков
     */
    private void startPlayersDecreasingHealthEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = 60; // Время до начала снижения здоровья игроков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateEvent("Сниж. здор. игроков " + timeToString(time));

                if (time-- <= 0) {
                    cancel();
                }
            }
        }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Конвертировать время типа Integer в тип String
     * @param time Время типа Integer
     * @return Время в типе String
     */
    public String timeToString(int time) {
        int min = time / 60 % 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);
    }
}