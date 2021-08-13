package net.Abdymazhit.SkyWarsRanked.game;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Менеджер игровых событий, отвечает за изменение игровых событий
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class GameEventsManager {

    /** Время до начала битвы */
    private static final int TIME_BEFORE_BATTLE_START = 10;

    /** Время до начала сужения игровой зоны */
    private static final int TIME_BEFORE_STARTING_NARROWING_GAME_ZONE = 50;

    /** Время до открытия мистического сундука */
    private static final int TIME_BEFORE_OPENING_MYSTERY_CHEST = 60;

    /** Время до закрытия мистического сундука */
    private static final int TIME_BEFORE_CLOSING_MYSTERY_CHEST = 30;

    /** Время до перезаполнения сундуков */
    private static final int TIME_BEFORE_REFILLING_CHESTS = 60;

    /** Время до конца сужения игровой зоны */
    private static final int TIME_BEFORE_ENDING_NARROWING_GAME_ZONE = 30;

    /** Время до второго перезаполнения сундуков */
    private static final int TIME_BEFORE_REFILLING_CHESTS2 = 60;

    /** Время до начала детматча */
    private static final int TIME_BEFORE_STARTING_DEATHMATCH = 60;

    /** Время до начала снижения здоровья игроков */
    private static final int TIME_BEFORE_DECREASING_PLAYERS_HEALTH = 60;

    /** Таймер обратного отсчета */
    public BukkitTask task;

    /** Время до заполнения сундуков */
    public int timeBeforeRefillingChests;

    /**
     * Начать игровое событие начала битвы
     */
    public void startBattleStartEvent() {
        // Начать обратный отсчет
        task = new BukkitRunnable() {
            int time = TIME_BEFORE_BATTLE_START; // Время до начала битвы

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Начало битвы " + timeToString(time));

                // Отправить сообщение в центр экрана о времени до начале битвы
                if(time == 5) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§f5", 4, 12, 4);
                    }
                } else if(time == 4) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§f4", 4, 12, 4);
                    }
                } else if(time == 3) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§e3", 4, 12, 4);
                    }
                } else if(time == 2) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§62", 4, 12, 4);
                    }
                } else if(time == 1) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§6§c1", 4, 12, 4);
                    }
                }

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time +
                        TIME_BEFORE_STARTING_NARROWING_GAME_ZONE +
                        TIME_BEFORE_OPENING_MYSTERY_CHEST +
                        TIME_BEFORE_CLOSING_MYSTERY_CHEST +
                        TIME_BEFORE_REFILLING_CHESTS;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
                    // Отправить сообщение в центр экрана о начале битвы
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§6ИГРА НАЧАЛАСЬ!", 4, 12, 4);
                    }

                    // Разрешить PvP между игроками
                    SkyWarsRanked.getGameManager().setEnabledPvP(true);

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
            int time = TIME_BEFORE_STARTING_NARROWING_GAME_ZONE; // Время до начала сужения зоны

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Начало суж. зоны " + timeToString(time));

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time +
                        TIME_BEFORE_OPENING_MYSTERY_CHEST +
                        TIME_BEFORE_CLOSING_MYSTERY_CHEST +
                        TIME_BEFORE_REFILLING_CHESTS;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
                    // Отправить сообщение в центр экрана о начале сужения зоны
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§fСужение зоны началось!", 4, 20, 4);
                    }

                    // Начать сужение зоны
                    WorldBorder worldBorder = Config.world.getWorldBorder();
                    worldBorder.setSize(40, 180);

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
            int time = TIME_BEFORE_OPENING_MYSTERY_CHEST; // Время до открытия мистического сундука

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Отк. Мист. сундука " + timeToString(time));

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time +
                        TIME_BEFORE_CLOSING_MYSTERY_CHEST +
                        TIME_BEFORE_REFILLING_CHESTS;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
                    // Отправить сообщение о открытии мистического сундука
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§dМистический сундук §aоткрыт§d! Поспешите забрать свои законные вещи!");
                    }

                    // Открыть мистический сундук
                    SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().open();

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
            int time = TIME_BEFORE_CLOSING_MYSTERY_CHEST; // Время до закрытия мистического сундука

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Закр. Мист. сундука " + timeToString(time));

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time +
                        TIME_BEFORE_REFILLING_CHESTS;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
                    // Отправить сообщение о закрытии мистического сундука
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§dМистический сундук §cзакрылся§d!");
                    }

                    // Закрыть мистический сундук
                    SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().close();

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
            int time = TIME_BEFORE_REFILLING_CHESTS; // Время до перезаполнения сундуков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));
                
                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
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
            int time = TIME_BEFORE_ENDING_NARROWING_GAME_ZONE; // Время до конца сужения зоны

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Конец суж. зоны " + timeToString(time));

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time +
                        TIME_BEFORE_REFILLING_CHESTS2;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
                    // Отправить сообщение в центр экрана о конце сужения зоны
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§fСужение зоны закончилось!", 4, 20, 4);
                    }

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
            int time = TIME_BEFORE_REFILLING_CHESTS2; // Время до перезаполнения сундуков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Перезап. сундуков " + timeToString(time));

                // Обновить таймер голограмм открытых сундуков
                timeBeforeRefillingChests = time;
                SkyWarsRanked.getGameManager().getChestManager().updateOpenedChestsHologramsTimer(timeBeforeRefillingChests);

                if (time-- <= 0) {
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
            int time = TIME_BEFORE_STARTING_DEATHMATCH; // Время до детматча

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Детматч " + timeToString(time));

                // Отправить сообщения в центр экрана о времени до начала детматча
                if(time == 5) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§f5", 4, 12, 4);
                    }
                } else if(time == 4) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§f4", 4, 12, 4);
                    }
                } else if(time == 3) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§e3", 4, 12, 4);
                    }
                } else if(time == 2) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§62", 4, 12, 4);
                    }
                } else if(time == 1) {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§6§c1", 4, 12, 4);
                    }
                }

                if (time-- <= 0) {
                    // Отправить сообщения в центр экрана о начале детматча
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        NMS.sendTitle(player, "§6§cДетматч!", 4, 12, 4);
                    }

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
            int time = TIME_BEFORE_DECREASING_PLAYERS_HEALTH; // Время до начала снижения здоровья игроков

            @Override
            public void run() {
                // Изменить таймер в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateEvent("Сниж. здор. игроков " + timeToString(time));

                if (time-- <= 0) {
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
                                cancel();
                            }
                        }
                    }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

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