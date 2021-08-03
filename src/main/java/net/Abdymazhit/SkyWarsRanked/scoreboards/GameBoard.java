package net.Abdymazhit.SkyWarsRanked.scoreboards;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Отвечает за scoreboard игры
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class GameBoard {

    /** Scoreboard игры */
    private final Scoreboard scoreboard;

    /**
     * Инициализирует scoreboard игры
     */
    public GameBoard() {
        ScoreboardManager scoreboardManager = SkyWarsRanked.getInstance().getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sw1", "sw2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§fКарта: §l" + Config.mapName).setScore(11);
        objective.getScore("   ").setScore(10);

        objective.getScore("§f§lСледующее событие:").setScore(9);

        Team event = scoreboard.registerNewTeam("event");
        event.addEntry("§a");
        objective.getScore("§a").setScore(8);

        objective.getScore("  ").setScore(7);

        Team players = scoreboard.registerNewTeam("livePlayers");
        players.addEntry("§f§lЖивых игроков: §a");
        objective.getScore("§f§lЖивых игроков: §a").setScore(6);

        Team kills = scoreboard.registerNewTeam("kills");
        kills.addEntry("§f§lУбийств: §a");
        objective.getScore("§f§lУбийств: §a").setScore(5);

        objective.getScore(" ").setScore(4);

        Team spectators = scoreboard.registerNewTeam("spectators");
        spectators.addEntry("§f§lЗрителей: §a");
        objective.getScore("§f§lЗрителей: §a").setScore(3);

        objective.getScore("").setScore(2);
        objective.getScore("§a§lVimeWorld.ru").setScore(1);

        objective.setDisplayName("§b§lSkyWars Ranked");
    }

    /**
     * Установить всем игрокам следующее событие в scoreboard'е игры с обратным отсчетом
     * @param text Текст обратного отсчета
     */
    public void updateEvent(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("event").setSuffix(text);
        }
    }

    /**
     * Обновить всем игрокам количество живых игроков в scoreboard'е лобби
     */
    public void updateLivePlayersCount() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("livePlayers").setSuffix(String.valueOf(SkyWarsRanked.getGameManager().getPlayers().size()));
        }
    }

    /**
     * Обновить игроку количество убийств в scoreboard'е лобби
     * @param player Игрок
     */
    public void updateKillsCount(Player player) {
        player.getScoreboard().getTeam("kills").setSuffix(String.valueOf(SkyWarsRanked.getGameManager().getPlayerInfo(player).getKills()));
    }

    /**
     * Обновить всем игрокам количество зрителей в scoreboard'е игры
     */
    public void updateSpectatorsCount() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("spectators").setSuffix(String.valueOf(SkyWarsRanked.getGameManager().getSpectators().size()));
        }
    }

    /**
     * Установить игроку scoreboard игры
     * @param player Игрок
     */
    public void setScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }
}