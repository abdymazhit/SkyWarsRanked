package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Менеджер {@link GameStage стадии игры}, отвечает за изменение {@link GameStage стадии игры}
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class GameStageManager {

    /**
     * Начать {@link GameStage стадию игры} WAITING
     */
    private void startWaitingStage() {
        // Установить стадию игры на WAITING
        SkyWarsRanked.getGameManager().setGameStage(GameStage.WAITING);

        // Установить статус scoreboard'а лобби на WAITING
        SkyWarsRanked.getLobbyBoard().setWaitingStatus();
    }

    /**
     * Попытаться начать {@link GameStage стадию игры} STARTING
     */
    public void tryStartStartingStage() {
        // Начать стадию STARTING, если набрано достаточное количество игроков
        if (SkyWarsRanked.getGameManager().getPlayers().size() == 2) {
            startStartingStage();
        }
    }

    /**
     * Начать {@link GameStage стадию игры} STARTING
     */
    private void startStartingStage() {
        // Установить стадию игры на STARTING
        SkyWarsRanked.getGameManager().setGameStage(GameStage.STARTING);

        // Начать обратный отсчет начала игры
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 15;

                    @Override
                    public void run() {
                        // Проверить, не вышли ли игроки с игры
                        if (SkyWarsRanked.getGameManager().getPlayers().size() < 2) {
                            // Установить игру на WAITING, так как игроки вышли из игры
                            startWaitingStage();
                            cancel();
                        } else {
                            // Изменить таймер начала игры в scoreboard'е лобби
                            SkyWarsRanked.getLobbyBoard().setStartingStatus("До начала: §a" + timeToString(time));

                            // Начать стадию игры GAME
                            if (time-- <= 0) {
                                startGameStage();
                                cancel();
                            }
                        }
                    }
                }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L)
        );
    }

    /**
     * Начать {@link GameStage стадию игры} GAME
     */
    private void startGameStage() {
        // Установить стадию игры на GAME
        SkyWarsRanked.getGameManager().setGameStage(GameStage.GAME);

        // Установить для игрока остров, если игрок не выбрал остров
        for(Player player : SkyWarsRanked.getGameManager().getPlayers()) {
            boolean hasIsland = false;

            for(Island island : Config.islands) {
                if(!hasIsland) {
                    if(island.getPlayer() != null) {
                        if(island.getPlayer().equals(player)) {
                            hasIsland = true;
                        }
                    }
                }
            }

            if(!hasIsland) {
                boolean islandSelected = false;

                for (Island island : Config.islands) {
                    if (!islandSelected) {
                        if (island.getPlayer() == null) {
                            island.setPlayer(player);
                            islandSelected = true;
                        }
                    }
                }
            }
        }

        // Перекинуть игроков в игру
        for(Island island : Config.islands) {
            if(island.getPlayer() != null) {
                Player player = island.getPlayer();

                // Очистить инвентарь игрока
                player.getInventory().clear();

                // Установить игровой режим на выживание
                player.setGameMode(GameMode.SURVIVAL);

                // Установить игрокам scoreboard игры
                SkyWarsRanked.getGameBoard().setScoreboard(player);

                // Установить игроку количество его убийств в scoreboard'е игры
                SkyWarsRanked.getGameBoard().updateKillsCount(player);

                // Телепортировать игрока в спавн острова
                player.teleport(island.getSpawn());
            }
        }

        // Перекинуть зрителей в игру
        for(Player player : SkyWarsRanked.getGameManager().getSpectators()) {
            // Установить зрителям scoreboard игры
            SkyWarsRanked.getGameBoard().setScoreboard(player);

            // Установить зрителям количество их убийств в scoreboard'е игры
            SkyWarsRanked.getGameBoard().updateKillsCount(player);

            // Телепортировать зрителей в место спавна зрителей
            player.teleport(Config.spectatorLocation);
        }

        // Обновить количество живых игроков в scoreboard'е игры
        SkyWarsRanked.getGameBoard().updateLivePlayersCount();

        // Обновить количество зрителей в scoreboard'е игры
        SkyWarsRanked.getGameBoard().updateSpectatorsCount();

        // Начать игровое событие начала битвы
        SkyWarsRanked.getGameEventsManager().startBattleStartEvent();
    }

    /**
     * Начать {@link GameStage стадию игры} ENDING
     */
    public void startEndingStage() {
        // Установить стадию игры на ENDING
        SkyWarsRanked.getGameManager().setGameStage(GameStage.ENDING);

        // Начать обратный отсчет конца игры
        SkyWarsRanked.getGameManager().setTask(
                new BukkitRunnable() {
                    int time = 15;

                    @Override
                    public void run() {
                        // Обновить таймер конца игры в scoreboard'е игры
                        SkyWarsRanked.getGameBoard().updateEvent("Конец игры " + timeToString(time));

                        // Завершить игру
                        if (time-- <= 0) {
                            for(Player player : Bukkit.getOnlinePlayers()) {
                                player.kickPlayer("Игра завершена");
                            }

                            Bukkit.getServer().unloadWorld("world", true);
                            Bukkit.shutdown();

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